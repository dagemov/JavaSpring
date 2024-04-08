package DagemovBackend.DaemovBackendEcommer.adapters.database.mapper;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T13:19:54-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ICategoryMapperEntityImpl implements ICategoryMapperEntity {

    @Override
    public Category toCategoryModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        String name = null;
        String description = null;
        LocalDateTime createdDate = null;
        LocalDateTime updatedDate = null;
        Long id = null;

        name = categoryEntity.getName();
        description = categoryEntity.getDescription();
        createdDate = categoryEntity.getCreatedDate();
        updatedDate = categoryEntity.getUpdatedDate();
        id = categoryEntity.getId();

        Category category = new Category( id, name, description, createdDate, updatedDate );

        return category;
    }

    @Override
    public CategoryEntity toCategoryEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( category.getName() );
        categoryEntity.setDescription( category.getDescription() );
        categoryEntity.setCreatedDate( category.getCreatedDate() );
        categoryEntity.setUpdatedDate( category.getUpdatedDate() );
        categoryEntity.setId( category.getId() );

        return categoryEntity;
    }

    @Override
    public List<Category> toCategoriesModel(List<CategoryEntity> categoryEntities) {
        if ( categoryEntities == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryEntities.size() );
        for ( CategoryEntity categoryEntity : categoryEntities ) {
            list.add( toCategoryModel( categoryEntity ) );
        }

        return list;
    }
}
