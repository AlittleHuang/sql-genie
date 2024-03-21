package io.github.genie.sql.data.example.service;

import io.github.genie.sql.data.access.Access;
import io.github.genie.sql.data.example.eneity.User;
import io.github.genie.sql.data.example.model.Page;
import io.github.genie.sql.data.example.model.Pageable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author HuangChengwei
 * @since 2024-03-19 14:01
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
        User updated = userService.updateRandomNumber(first.getRandomNumber());
        Assertions.assertNotEquals(first.getOptLock(), updated.getOptLock());
    }

    @Test
    void page() {
        Page<User> page = userService.page(null, Pageable.of(1, 12));
        System.out.println(page);
        page = userService.page("Marjorie Minnie", Pageable.of(1, 12));
        System.out.println(page);
    }
}