package DagemovBackend.DaemovBackendEcommer.domain.api.usecase;

import DagemovBackend.DaemovBackendEcommer.dataTest.TestCategoryData;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import DagemovBackend.DaemovBackendEcommer.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private UseCaseCategory useCaseCategory;
    private List<Category> categories= TestCategoryData.createCategory();

    @Test
    @DisplayName("Test successful to add category")
    void shouldAddCategory()
    {
        //GIVEN
        //Category category = new Category(10L, "Category Test","Description Unit Test", LocalDateTime.now(),null);
        //WHEN
        when(categoryPersistencePort.addCategory(categories.get(0))).thenReturn(categories.get(0));
        useCaseCategory.addCategory(categories.get(0));
        //THEN
        verify(categoryPersistencePort).addCategory(categories.get((0)));
    }
    @Test
    @DisplayName("Test successful to get category by id")
    void ShouldGetCategoryById(){
        //GIVEN
        Category category = categories.get(0);
        //WHEN
        when(categoryPersistencePort.getCategoryById(category.getId())).thenReturn(Optional.of(category));
        useCaseCategory.getCategoryById(category.getId());
        //THEN
        verify(categoryPersistencePort).getCategoryById(category.getId());
    }
    @Test
    @DisplayName("Test successful to get category by name")
    void ShouldGetCategoryByName(){
        //GIVEN
        Category category = categories.get(0);
        //WHEN
        when(categoryPersistencePort.getCategoryByName(category.getName())).thenReturn(Optional.of(category));
        useCaseCategory.getCategoryByName(category.getName());
        //THEN
        verify(categoryPersistencePort).getCategoryByName(category.getName());
    }
    @Test
    @DisplayName("Test successful to update category")
    void ShouldUpdateCategory(){
        // GIVEN
        //Remember like in the use case we must past the Id WITH LONG , not with get , cuz that don't work.
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Updated Category", "Description", LocalDateTime.now(), null);

        // Mock the behavior of getCategoryById to return a non-empty Optional
        when(categoryPersistencePort.getCategoryById(categoryId)).thenReturn(Optional.of(category));

        // WHEN
        Category updatedCategory = useCaseCategory.updateCategory(categoryId, category);

        // THEN
        verify(categoryPersistencePort).updateCategory(categoryId, category);
    }
    @Test
    @DisplayName("Test successful to get all categories")
    void  ShouldGetAllCategories(){
        //GIVEN
        //WHEN
        when(categoryPersistencePort.getAllCategories(0,10,true)).thenReturn(categories);
        useCaseCategory.getAllCategories(0,10,true);
        //THEN
        verify(categoryPersistencePort).getAllCategories(0,10,true);
    }
    @Test
    @DisplayName("Test successful to delete category")
    void ShouldDeleteCategory(){
        //GIVEN
        Long id = 1L;
        Category category = categories.get(0);
        //WHEN
        when(categoryPersistencePort.getCategoryById(id)).thenReturn(Optional.of(category));
        useCaseCategory.deleteCategory(id);
        //THEN
        verify(categoryPersistencePort).deleteCategory(id);
    }

}
