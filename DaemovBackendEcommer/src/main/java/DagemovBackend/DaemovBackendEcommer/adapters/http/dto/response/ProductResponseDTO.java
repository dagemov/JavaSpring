package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductResponseDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final float price;
    private final String urlImage;
    private final int stock;
    private final UserEntity userEntity;
    private final CategoryEntity categoryEntity;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}
