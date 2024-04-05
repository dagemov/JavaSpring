package DagemovBackend.DaemovBackendEcommer.adapters.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50,message = "The field only can have max 50 characters")
    @Column(unique = true)
    private String name;
    @Size(max = 250,message = "The field only can have max 250 characters")
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
