package io.github.genie.sql.data.jpa.example.service;

import io.github.genie.sql.data.access.Access;
import io.github.genie.sql.data.jpa.example.eneity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author HuangChengwei
 * @since 2024-03-19 16:45
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    Access<User, Long> userAccess;

    @Test
    void getByUsername() {
        User first = userAccess.getFirst();
        if (first != null) {
            List<User> users = userService.getByUsername(first.getUsername());
            Assertions.assertFalse(users.isEmpty());
            for (User user : users) {
                Assertions.assertEquals(user.getUsername().toLowerCase(), first.getUsername().toLowerCase());
            }
        }
    }

    @Test
    void updateRandomNumber() {
        User first = userAccess.getFirst();
        User updated = userService.updateRandomNumber(first.getId());
        Assertions.assertNotEquals(first.getOptLock(), updated);
    }
}