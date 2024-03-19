package io.github.genie.sql.data.jdbc.example.model;

import io.github.genie.sql.api.ExpressionHolder;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.data.jdbc.example.eneity.Gender;
import io.github.genie.sql.data.jdbc.example.eneity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author HuangChengwei
 * @since 2024-03-19 16:52
 */
@Getter
@Setter
public class UserQuery implements PageRequest<User> {

    private String username;
    private String parentUsername;
    private Gender gender;
    private Integer page;
    private Integer size;

    @Override
    public ExpressionHolder<User, Boolean> apply(Root<User> root) {
        return root
                .whereIf(username != null, r -> r.get(User::getUsername).eq(username))
                .andIf(gender != null, r -> r.get(User::getGender).eq(gender))
                .andIf(parentUsername != null, r -> r.get(User::getParentUser).get(User::getUsername).eq(parentUsername));
    }


}
