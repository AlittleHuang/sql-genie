package io.github.genie.sql.data.example.model;

import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.builder.util.Paths;
import io.github.genie.sql.data.example.eneity.Gender;
import io.github.genie.sql.data.example.eneity.User;
import lombok.Data;

/**
 * @author HuangChengwei
 * @since 2024-03-19 16:52
 */
@Data
public class UserQuery implements PageablePredicate<User> {

    private String username;
    private String parentUsername;
    private Gender gender;
    private Integer page;
    private Integer size;

    @Override
    public TypedExpression<User, Boolean> predicate() {
        return Paths.get(User::getUsername).eqIfNotNull(username)
                .and(User::getGender).eqIfNotNull(gender)
                .and(User::getParentUser).get(User::getUsername).eqIfNotNull(parentUsername);
    }
}
