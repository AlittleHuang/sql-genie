package io.github.genie.sql.data.example.eneity;

import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.EntityPathExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static io.github.genie.sql.builder.util.Paths.get;
import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@ToString
@Getter
@Setter
public class User {

    public static StringPathExpression<User> Username = get(User::getUsername);
    public static EntityPathExpression<User, User> ParentUser = get(User::getParentUser);
    public static ComparablePathExpression<User, Gender> Gender = get(User::getGender);
    public static NumberPathExpression<User, Long> Pid = get(User::getPid);

    @Id
    private Long id;

    private Integer randomNumber;

    private String username;

    private Date time;

    private Long pid;

    private Double timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    @ToString.Exclude
    private User parentUser;

    private boolean valid;

    private Gender gender;

    private Date instant;

    private Long testLong;

    private Integer testInteger;

    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;


    @Version
    private int optLock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "randomNumber", updatable = false, insertable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    @ToString.Exclude
    private User randomUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testInteger", updatable = false, insertable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    @ToString.Exclude
    private User testUser;

    public User() {
    }

}
