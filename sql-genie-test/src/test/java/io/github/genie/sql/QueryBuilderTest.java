package io.github.genie.sql;

import io.github.genie.sql.api.Lists;
import io.github.genie.sql.api.Query;
import io.github.genie.sql.api.Query.OrderBy;
import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.api.Query.Where;
import io.github.genie.sql.api.TypedExpression.BooleanExpression;
import io.github.genie.sql.builder.Q;
import io.github.genie.sql.entity.User;
import io.github.genie.sql.projection.IUser;
import io.github.genie.sql.projection.UserInterface;
import io.github.genie.sql.projection.UserModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.genie.sql.builder.Q.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class QueryBuilderTest {

    static List<User> users() {
        return UserDaoProvider.users();
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void select(Select<User> userQuery) {

        IUser first1 = userQuery.select(IUser.class).getFirst(20);
        System.out.println(first1);
        User first3 = userQuery.fetch(User::getParentUser).getFirst(20);
        System.out.println(first3);
        System.out.println(first3.getParentUser());
        IUser.U first2 = userQuery.select(IUser.U.class).getFirst(20);
        System.out.println(first2);

        User first = userQuery.select(User.class).getFirst();
        assertEquals(first, users().get(0));

        Integer firstUserid = userQuery.select(User::getId).getFirst();
        assertEquals(firstUserid, users().get(0).getId());

        Object[] array = userQuery.select(User::getId, User::getRandomNumber).getFirst();
        assertEquals(array[0], users().get(0).getId());
        assertEquals(array[1], users().get(0).getRandomNumber());

        UserModel model = userQuery.select(UserModel.class).getFirst();
        assertEquals(model, new UserModel(users().get(0)));

        UserInterface ui = userQuery.select(UserInterface.class).getFirst();
        assertEquals(model.asMap(), ui.asMap());

        Long count = userQuery.select(Q.get(User::getId).count()).getSingle();
        assertEquals(count, users().size());

        Object[] aggArray = userQuery.select(Lists.of(
                Q.get(User::getId).count(),
                Q.get(User::getRandomNumber).max(),
                Q.get(User::getRandomNumber).min(),
                Q.get(User::getRandomNumber).sum(),
                Q.get(User::getRandomNumber).avg()
        )).getSingle();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (User user : users()) {
            int number = user.getRandomNumber();
            max = Math.max(max, number);
            min = Math.min(min, number);
            sum += number;
        }

        assertEquals(aggArray[0], (long) users().size());
        assertEquals(aggArray[1], max);
        assertEquals(aggArray[2], min);
        assertEquals(((Number) aggArray[3]).intValue(), sum);
        assertEquals(
                asBigDecimal(aggArray[4]),
                asBigDecimal(sum * 1.0 / users().size())
        );

    }

    BigDecimal asBigDecimal(Object num) {
        return new BigDecimal(num.toString()).setScale(4, RoundingMode.HALF_UP);
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void selectDistinct(Select<User> userQuery) {
        List<Integer> list = userQuery.selectDistinct(User::getRandomNumber)
                .getList();
        List<Integer> collect = users()
                .stream().map(User::getRandomNumber)
                .distinct()
                .collect(Collectors.toList());
        assertEquals(list, collect);
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void fetch(Select<User> userQuery) {
        List<User> users = userQuery.fetch(User::getParentUser).getList();

        assertEquals(users, users());
        for (int i = 0; i < users().size(); i++) {
            User a = users.get(i);
            User b = users().get(i);
            if (b.getParentUser() != null) {
                assertEquals(b.getParentUser(), a.getParentUser());
            } else {
                assertNull(a.getParentUser());
            }
        }

        users = userQuery.fetch(Q.get(User::getParentUser).get(User::getParentUser))
                .getList();

        assertEquals(users, users());
        for (int i = 0; i < users().size(); i++) {
            User a = users.get(i);
            User b = users().get(i);
            if (b.getParentUser() != null) {
                b = b.getParentUser();
                a = a.getParentUser();
                assertNotNull(a);
                if (b.getParentUser() != null) {
                    assertEquals(b.getParentUser(), a.getParentUser());
                }
            }
        }

    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void groupBy(Select<User> userQuery) {
        List<Object[]> list = userQuery
                .select(Lists.of(
                        Q.get(User::getRandomNumber),
                        Q.get(User::getId).count()
                ))
                .groupBy(User::getRandomNumber)
                .getList();

        Map<Object, Long> count = users().stream()
                .collect(Collectors.groupingBy(User::getRandomNumber, Collectors.counting()));

        assertEquals(list.size(), count.size());
        for (Object[] objects : list) {
            Long value = count.get(objects[0]);
            assertEquals(value, objects[1]);
        }

    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void orderBy(Select<User> userQuery) {
        for (Checker<User, OrderBy<User, User>> checker : getWhereTestCase(new Checker<>(users(), userQuery))) {
            List<User> users = checker.collector.orderBy(User::getRandomNumber, User::getId).asc()
                    .getList();
            ArrayList<User> sorted = new ArrayList<>(checker.result);
            sorted.sort(Comparator.comparingInt(User::getRandomNumber));
            assertEquals(users, sorted);
            users = checker.collector.orderBy(User::getRandomNumber, User::getId)
                    .getList();
            assertEquals(users, sorted);
            users = checker.collector.orderBy(User::getRandomNumber, User::getId).desc()
                    .getList();
            sorted = new ArrayList<>(checker.result);
            sorted.sort((a, b) -> {
                int compare = Integer.compare(b.getRandomNumber(), a.getRandomNumber());
                if (compare == 0) {
                    compare = Integer.compare(b.getId(), a.getId());
                }
                return compare;
            });
            assertEquals(users, sorted);

            users = checker.collector.orderBy(User::getRandomNumber).desc()
                    .orderBy(User::getId)
                    .getList();
            sorted = new ArrayList<>(checker.result);
            sorted.sort((a, b) -> {
                int compare = Integer.compare(b.getRandomNumber(), a.getRandomNumber());
                if (compare == 0) {
                    compare = Integer.compare(a.getId(), b.getId());
                }
                return compare;
            });
            assertEquals(users, sorted);
        }

    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void getList(Select<User> userQuery) {

        List<User> users = userQuery.getList();
        assertEquals(users.size(), users().size());

        users = userQuery.getList(0, 10);
        assertEquals(users, users().subList(0, 10));

        users = userQuery.getList(100, 15);
        assertEquals(users, users().subList(100, 115));

    }


    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void where(Select<User> userQuery) {
        Checker<User, Where<User, User>> check = new Checker<>(users(), userQuery);
        getWhereTestCase(check);
    }

    private List<Checker<User, OrderBy<User, User>>> getWhereTestCase(Checker<User, Where<User, User>> check) {
        List<Checker<User, OrderBy<User, User>>> result = new ArrayList<>();
        String username = users().get(10).getUsername();

        Where<User, User> userQuery = check.collector;
        OrderBy<User, User> collector = userQuery
                .where(get(User::getParentUser).get(User::getUsername).eq(username));
        Stream<User> stream = newStream(check)
                .filter(user -> user.getParentUser() != null && username.equals(user.getParentUser().getUsername()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        stream = newStream(check)
                .filter(user -> user.getParentUser() != null && !username.equals(user.getParentUser().getUsername()));
        collector = userQuery
                .where(get(User::getParentUser).get(User::getUsername).ne(username));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery
                .where(get(User::getUsername).ne(username));
        stream = newStream(check)
                .filter(user -> !username.equals(user.getUsername()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery
                .where(get(User::getUsername).ne(username));
        stream = newStream(check)
                .filter(user -> !username.equals(user.getUsername()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery
                .where(get(User::getUsername).ne(username));
        stream = newStream(check)
                .filter(user -> !username.equals(user.getUsername()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));


        BooleanExpression<User> isValid = get(User::isValid);
        collector = userQuery.where(isValid);
        stream = users().stream().filter(User::isValid);

        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).eq(2));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() == 2);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getPid).ne(2));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getPid() != null && user.getPid() != 2);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).in(1, 2, 3));
        stream = newStream(check).filter(User::isValid).filter(user -> Arrays.asList(1, 2, 3).contains(user.getRandomNumber()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).notIn(1, 2, 3));
        stream = newStream(check).filter(User::isValid).filter(user -> !Arrays.asList(1, 2, 3).contains(user.getRandomNumber()));
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getPid).isNull());
        stream = newStream(check).filter(User::isValid).filter(user -> user.getPid() == null);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).ge(10));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() >= 10);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).gt(10));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() > 10);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).le(10));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() <= 10);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).lt(10));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() < 10);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).between(10, 15));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() >= 10 && user.getRandomNumber() <= 15);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).notBetween(10, 15));
        stream = newStream(check).filter(User::isValid).filter(user -> user.getRandomNumber() < 10 || user.getRandomNumber() > 15);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid
                .and(User::getRandomNumber).notBetween(10, 15)
                .and(User::getId).mod(3).eq(0)
        );
        stream = newStream(check).filter(User::isValid).filter(user ->
                !(user.getRandomNumber() >= 10 && user.getRandomNumber() <= 15)
                        && user.getId() % 3 == 0);
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).ge(get(User::getPid)));
        stream = newStream(check).filter(User::isValid)
                .filter(user -> user.getPid() != null && user.getRandomNumber() >= user.getPid());
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).gt(get(User::getPid)));
        stream = newStream(check).filter(User::isValid)
                .filter(user -> user.getPid() != null && user.getRandomNumber() > user.getPid());
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).le(get(User::getPid)));
        stream = newStream(check).filter(User::isValid)
                .filter(user -> user.getPid() != null && user.getRandomNumber() <= user.getPid());
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber).lt(get(User::getPid)));
        stream = newStream(check).filter(User::isValid)
                .filter(user -> user.getPid() != null && user.getRandomNumber() < user.getPid());
        addTestCaseAndCheck(result, new Checker<>(stream, collector));

        collector = userQuery.where(isValid.and(User::getRandomNumber)
                .between(get(User::getRandomNumber), get(User::getPid)));
        stream = newStream(check).filter(User::isValid)
                .filter(user -> user.getPid() != null && user.getRandomNumber() >= user.getRandomNumber() && user.getRandomNumber() <= user.getPid());
        addTestCaseAndCheck(result, new Checker<>(stream, collector));
        return result;
    }

    private static <T, U extends Query.Collector<T>> void addTestCaseAndCheck(List<Checker<T, U>> result, Checker<T, U> checker) {
        result.add(checker);
        checker.check();
    }

    private static <T> Stream<T> newStream(Checker<T, ?> check) {
        return check.result.stream();
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void count(Select<User> userQuery) {
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void requiredCountSubQuery(Select<User> userQuery) {
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void queryList(Select<User> userQuery) {
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void buildMetadata(Select<User> userQuery) {
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void having(Select<User> userQuery) {
    }

    @ParameterizedTest
    @ArgumentsSource(UserDaoProvider.class)
    void root(Select<User> userQuery) {
    }


    static class Checker<T, U extends Query.Collector<T>> {
        List<T> result;

        U collector;

        Checker(Stream<T> result, U collector) {
            this(result.collect(Collectors.toList()), collector);
        }

        Checker(List<T> result, U collector) {
            this.result = result;
            this.collector = collector;
        }

        void check() {
            assertEquals(result, collector.getList());
        }

    }
}