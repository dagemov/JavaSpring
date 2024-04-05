package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCategoryDTO {
    @NotBlank(message = Constants.REQUIRED_INPUT+" Name")
    @Size(max = 50,message = "The field only can have max 50 characters")
    private String name;
    @Size(max = 250,message = "The field only can have max 250 characters")
    private String description;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
