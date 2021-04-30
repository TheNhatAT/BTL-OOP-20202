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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 6, message = "Password must have more than 6 characters")
    @Column(name = "password")
    private String password;

    @Size(min = 2, max = 60, message = "Size of the first name must be lie between 2 and 30")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 30, message = "Size of the last name must be lie between 2 and 30")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email must follow the formatter: ***@***")
    @NotBlank(message = "Email can not be null")
    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "account_verrified")
    private boolean accountVerified;

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;
}
