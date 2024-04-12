package DagemovBackend.DaemovBackendEcommer.adapters.database.entity;

import DagemovBackend.DaemovBackendEcommer.domain.model.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50,message = "The field only can have max 50 characters")
    private String firstName;
    @Size(max = 50,message = "The field only can have max 50 characters")
    private String lastName;
    @Column(unique = true)
    @Size(max = 150,message = "The field only can have max 150 characters")
    private String email;
    @Size(max = 150,message = "The field only can have max 150 characters")
    private String password;
    @Size(max = 80,message = "The field only can have max 80 characters")
    private String address;
    @Size(max = 10,message = "The field only can have max 10 characters to Phone Numbers")
    private String phoneNumber;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private UserType userType;
}
