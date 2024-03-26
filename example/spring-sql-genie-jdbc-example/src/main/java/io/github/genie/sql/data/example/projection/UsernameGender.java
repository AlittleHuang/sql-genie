package io.github.genie.sql.data.example.projection;

import io.github.genie.sql.data.example.eneity.Gender;
import lombok.Data;

/**
 * @author HuangChengwei
 * @since 2024-03-22 10:23
 */
@Data
public class UsernameGender {
    Long id;
    String username;
    Gender gender;
}
