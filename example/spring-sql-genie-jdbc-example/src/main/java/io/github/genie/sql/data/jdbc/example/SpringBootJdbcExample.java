package io.github.genie.sql.data.jdbc.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author HuangChengwei
 * @since 2024-03-19 13:50
 */
@SpringBootApplication
public class SpringBootJdbcExample {
    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(SpringBootJdbcExample.class, args);
    }
}
