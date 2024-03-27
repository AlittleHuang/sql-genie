package io.github.genie.sql.data.example.dao;

import io.github.genie.sql.data.access.AbstractAccess;
import io.github.genie.sql.data.example.eneity.Company;
import org.springframework.stereotype.Repository;

/**
 * @author HuangChengwei
 * @since 2024-03-27 14:24
 */
@Repository
public class CompanyRepository extends AbstractAccess<Company, Integer> {
}
