package thenhat.code.managerwebapp.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, message = "{user.password}")
    @Column(name = "password")
    private String password;

    @Size(min = 2, max = 60, message = "{user.first.name}")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 30, message = "{user.last.name}")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "{user.format.email.address}")
    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "account_verrified")
    private boolean accountVerified;

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Transient
    private String fullName;
    @Transient
    private String role;
}
