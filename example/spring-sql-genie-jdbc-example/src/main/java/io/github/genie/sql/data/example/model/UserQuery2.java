package io.github.genie.sql.data.example.model;

import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.data.example.eneity.Gender;
import io.github.genie.sql.data.example.eneity.User;
import lombok.Data;

/**
 * @author HuangChengwei
 * @since 2024-03-19 16:52
 */
@Data
public class UserQuery2 implements PageablePredicate<User> {

    private String username;
    private String parentUsername;
    private Gender gender;
    private Integer page;
    private Integer size;

    @Override
    public TypedExpression<User, Boolean> predicate() {
        return User.Username.eqIfNotNull(username)
                .or(User.ParentUser.get(User.Username).eqIfNotNull(parentUsername))
                .or(User.Gender.eqIfNotNull(gender));
    }
}