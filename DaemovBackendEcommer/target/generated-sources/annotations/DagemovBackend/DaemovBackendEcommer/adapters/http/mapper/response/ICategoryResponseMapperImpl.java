package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.CategoryResponseDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T13:19:53-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ICategoryResponseMapperImpl implements ICategoryResponseMapper {

    @Override
    public CategoryResponseDTO toResponseCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        LocalDateTime createdDate = null;
        LocalDateTime updatedDate = null;

        id = category.getId();
        name = category.getName();
        createdDate = category.getCreatedDate();
        updatedDate = category.getUpdatedDate();

        String description = null;

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO( id, name, description, createdDate, updatedDate );

        return categoryResponseDTO;
    }

    @Override
    public List<CategoryResponseDTO> toCategoriesDTOS(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponseDTO> list = new ArrayList<CategoryResponseDTO>( categories.size() );
        for ( Category category : categories ) {
            list.add( toResponseCategoryDTO( category ) );
        }

        return list;
    }
}
