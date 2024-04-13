package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddProductDTO {
    @NotBlank(message = Constants.REQUIRED_INPUT+" Name")
    @Size(max = 50,message = "The field only can have max 50 characters")
    private String name;

    @NotBlank(message = Constants.REQUIRED_INPUT+" Description of product")
    @Size(max = 250,message = "The field only can have max 50 characters")
    private String description;
    private String urlImage;

    @NotBlank(message = Constants.REQUIRED_INPUT+" Price of product")
    private Float price;
    @NotBlank(message = Constants.REQUIRED_INPUT+" Stock of product")
    private int stock;

    private CategoryEntity categoryEntity;
    private UserEntity userEntity;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
