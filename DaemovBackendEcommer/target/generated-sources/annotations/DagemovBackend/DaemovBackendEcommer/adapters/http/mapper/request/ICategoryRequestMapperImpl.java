package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddCategoryDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T13:19:54-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ICategoryRequestMapperImpl implements ICategoryRequestMapper {

    @Override
    public Category addCategory(AddCategoryDTO addCategoryDTO) {
        if ( addCategoryDTO == null ) {
            return null;
        }

        String name = null;
        String description = null;
        LocalDateTime createdDate = null;
        LocalDateTime updatedDate = null;

        name = addCategoryDTO.getName();
        description = addCategoryDTO.getDescription();
        createdDate = addCategoryDTO.getCreatedDate();
        updatedDate = addCategoryDTO.getUpdatedDate();

        Long id = null;

        Category category = new Category( id, name, description, createdDate, updatedDate );

        return category;
    }
}
