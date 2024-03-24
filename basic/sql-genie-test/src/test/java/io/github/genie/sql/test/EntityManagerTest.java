package io.github.genie.sql.test;

import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.test.entity.UserSummary;

import java.util.List;

public class EntityManagerTest {

    public static void main(String[] args) {
        Select<UserSummary> from = UserQueryProvider.jpaQuery().from(UserSummary.class);
        List<UserSummary> list = from.where(UserSummary::getMaxRandomNumber).eq(33).getList();
        System.out.println(list);

    }

}
