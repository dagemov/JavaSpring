package DagemovBackend.DaemovBackendEcommer.domain.spi;

import DagemovBackend.DaemovBackendEcommer.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    Category addCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    Optional<Category> getCategoryByName(String name);
    Category updateCategory(Long id,Category category);
    void deleteCategory(Long id);
    List<Category> getAllCategories(Integer page, Integer size, boolean orderAsc);
}