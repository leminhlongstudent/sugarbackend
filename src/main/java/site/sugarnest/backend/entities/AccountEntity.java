package site.sugarnest.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Table(name = "accounts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String isDelete;

    private String isActive;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createAt;

    private String image;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date deleteAt;

    private String typeName;

    private String idOther;

    private String currentPassword;

    @Column(columnDefinition = "int default '0'")
    private Integer number_login_fail;

    @ManyToMany
    private Set<RoleEntity> roles;

    public void setCreateAt() {
        this.createAt = LocalDateTime.now();
    }

    public void setUpdateAt() {
        this.updateAt = LocalDateTime.now();
    }

    @Column(columnDefinition = "VARCHAR(255)")
    private String verificationCode;

    @Column(columnDefinition = "VARCHAR(255)")
    private String enabled;
}
