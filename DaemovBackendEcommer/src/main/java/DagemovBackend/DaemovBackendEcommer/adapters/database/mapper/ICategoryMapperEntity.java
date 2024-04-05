package DagemovBackend.DaemovBackendEcommer.adapters.database.mapper;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import org.mapstruct.*;

import java.util.List;
@Mapper( componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapperEntity {
    @Mappings({
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "description",source = "description"),
            @Mapping(source = "createdDate",target = "createdDate"),
            @Mapping(source = "updatedDate",target = "updatedDate")
    })
    Category toCategoryModel(CategoryEntity categoryEntity);
    @InheritInverseConfiguration
    CategoryEntity toCategoryEntity(Category category);
    List<Category> toCategoriesModel(List<CategoryEntity> categoryEntities);
}
