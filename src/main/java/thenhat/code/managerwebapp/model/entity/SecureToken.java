package thenhat.code.managerwebapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secure_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecureToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timestamp;

    @Column(updatable = false, name = "expire_at")
    @Basic(optional = false) //-- marked that field not null --
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Users user;

    //-- this field is not included in DB --
    @Transient
    private boolean isExpired;

    public boolean isExpired(){
        return getExpireAt().isBefore(LocalDateTime.now());
    }

}
