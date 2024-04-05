package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.CategoryResponseDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponseDTO toResponseCategoryDTO(Category category);
    List<CategoryResponseDTO> toCategoriesDTOS(List<Category> categories);
}
