package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request;


import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddCategoryDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "description",source = "description"),
            @Mapping(target = "createdDate",source = "createdDate"),
            @Mapping(target = "updatedDate",source = "updatedDate"),
    })
    Category addCategory(AddCategoryDTO addCategoryDTO);
}
