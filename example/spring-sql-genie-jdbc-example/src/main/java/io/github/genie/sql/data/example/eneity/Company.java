package io.github.genie.sql.data.example.eneity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Company {

    @Id
    private Integer id;
    private String name;
    private String addr;

}
