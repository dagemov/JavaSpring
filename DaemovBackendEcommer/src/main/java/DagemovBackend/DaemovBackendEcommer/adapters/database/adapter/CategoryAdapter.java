package DagemovBackend.DaemovBackendEcommer.adapters.database.adapter;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.ICategoryMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.ICategoryRepository;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import DagemovBackend.DaemovBackendEcommer.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapperEntity categoryMapperEntity;

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryMapperEntity categoryMapperEntity) {
        this.categoryRepository = categoryRepository;
        this.categoryMapperEntity = categoryMapperEntity;
    }

    @Override
    public Category addCategory(Category category) {
        categoryRepository.save(categoryMapperEntity.toCategoryEntity(category));
        return category;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Optional<CategoryEntity> categoryOptional= categoryRepository.findById(id);
        return categoryOptional.map(categoryMapperEntity::toCategoryModel);
    }

    @Override
    public Optional<Category> getCategoryByName(String name)
    {
        Optional<CategoryEntity> categoryOptional= categoryRepository.findByName(name);
        return categoryOptional.map(categoryMapperEntity::toCategoryModel);
    }

    @Override
    public Category updateCategory(Long id,Category category)
    {
        categoryRepository.save(categoryMapperEntity.toCategoryEntity(category));
        return category;
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);
    }


    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean orderAsc)
    {
        Sort sort = orderAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        List<CategoryEntity> categoriesEntity = categoryRepository.findAll(pageable).getContent();
        List<Category> categories = categoryMapperEntity.toCategoriesModel(categoriesEntity);
        if(categories.isEmpty())
        {
            throw new EmptyFile(Constants.NO_RECORD_FOUND);
        }
        return categories;
    }
}
