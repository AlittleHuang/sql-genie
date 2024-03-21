package io.github.genie.sql.data.example.service;

import io.github.genie.sql.data.access.Access;
import io.github.genie.sql.data.example.eneity.User;
import io.github.genie.sql.data.example.model.Page;
import io.github.genie.sql.data.example.model.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author HuangChengwei
 * @since 2024-03-19 13:55
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final Access<User, Long> userAccess;

    public List<User> getByUsername(String username) {
        return userAccess.where(User::getUsername).eq(username).getList();
    }

    public User updateRandomNumber(long userId) {
        User user = userAccess.get(userId);
        return updateRandomNumber(user);
    }

    public User updateRandomNumber(User user) {
        if (user != null) {
            user.setRandomNumber(ThreadLocalRandom.current().nextInt(20));
        }
        userAccess.update(user);
        return user;
    }

    public Page<User> page(String username, Pageable<User> pageable) {
        return userAccess
                .where(User::getUsername).eqIfNotNull(username)
                .where(User::getUsername).eqIfNotNull(username)
                .slice(pageable);
    }

}
