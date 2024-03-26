package io.github.genie.sql.test.entity;

import io.github.genie.sql.builder.meta.SubSelect;
import lombok.Data;

import javax.persistence.Id;

@Data
@SubSelect("SELECT u.username AS username, max(u.random_number) AS max_random_number, count( u.id ) AS count FROM `user` u GROUP BY u.username")
public class UserSummary {
    @Id
    private String username;
    private Integer maxRandomNumber;
    private Long count;
}