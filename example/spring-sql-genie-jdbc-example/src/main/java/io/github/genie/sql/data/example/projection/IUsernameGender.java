package io.github.genie.sql.data.example.projection;

import io.github.genie.sql.data.example.eneity.Gender;

/**
 * @author HuangChengwei
 * @since 2024-03-22 10:23
 */
public interface IUsernameGender {

    Long getId();

    String getUsername();

    Gender getGender();
}
