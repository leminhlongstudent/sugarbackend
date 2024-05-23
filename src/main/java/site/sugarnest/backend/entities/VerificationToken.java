package site.sugarnest.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = AccountEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_account")
    private AccountEntity accountEntity;

    private Date expiryDate;
}
