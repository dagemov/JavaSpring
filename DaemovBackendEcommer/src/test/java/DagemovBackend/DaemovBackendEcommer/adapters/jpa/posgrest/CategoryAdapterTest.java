package DagemovBackend.DaemovBackendEcommer.adapters.jpa.posgrest;

import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.CategoryAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.ICategoryMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.ICategoryRepository;
import DagemovBackend.DaemovBackendEcommer.dataTest.TestCategoryData;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ICategoryMapperEntity categoryMapperEntity;
    @InjectMocks
    private CategoryAdapter categoryAdapter;
    private List<Category> categories= TestCategoryData.createCategory();

    @Test
    @DisplayName("Test successful to add category")
    void shouldAddCategory()
    {
        //GIVEN
        Category category = categories.get(1);
        //WHEN
        lenient().when(categoryRepository.findByName(any())).thenReturn(Optional.empty()); //Lenient is to delete the inccessaries beans on the test , to mokito dont't set exceptions
        categoryAdapter.addCategory(category);
        //THEN
        verify(categoryRepository,times(1)).save(any());
    }
    @Test
    @DisplayName("Test successful to update category")
    void shouldUpdateCategory(){
        Long id = 1L;
        //GIVEN
        Category category = categories.get(1);
        category.setId(id);
        //WHEN
        lenient().when(categoryRepository.findByName(any())).thenReturn(Optional.empty());
        categoryAdapter.updateCategory(id,category);
        //THEN
        verify(categoryRepository,times(1)).save(any());
    }
    @Test
    @DisplayName("Test successful to get category by id")
    void shouldGetCategoryById(){
        //GIVEN
        Long categoryId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity(categoryId, "Category Test","Description Unit Test", LocalDateTime.now(),null);
        Category category = categories.get(1);
        category.setId(categoryId);

        //WHEN
        lenient().when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        Optional<Category> categoryOptional = categoryAdapter.getCategoryById(categoryId);

        //THEN
        verify(categoryRepository, times(1)).findById(categoryId);

    }
    @Test
    @DisplayName("Test successful to get category by name")
    void shouldGetCategoryByName(){
        //GIVEN
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Test","Description Unit Test", LocalDateTime.now(),null);
        Category category = categories.get(1);
        //WHEN
        lenient().when(categoryRepository.findByName(any())).thenReturn(Optional.of(categoryEntity));
        Optional<Category> categoryOptional = categoryAdapter.getCategoryByName(category.getName());
        //THEN
        verify(categoryRepository, times(1)).findByName(any());
        //
    }
    @Test
    @DisplayName("Test successful to get all categories")
    void shouldGetAllCategories(){
        //GIVEN
        Integer page = 0;
        Integer size = 10;
        boolean orderAsc = true;
        List<CategoryEntity> categoriesEntity = Arrays.asList(
                new CategoryEntity(1L, "Category 1", "Description 1", LocalDateTime.now(), null),
                new CategoryEntity(2L, "Category 2", "Description 2", LocalDateTime.now(), null)
        );
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1", LocalDateTime.now(), null),
                new Category(2L, "Category 2", "Description 2", LocalDateTime.now(), null)
        );
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderAsc ? "name" : "name").ascending());
        Page<CategoryEntity> pageCategoriesEntity = new PageImpl<>(categoriesEntity, pageable, categoriesEntity.size());

        when(categoryRepository.findAll(pageable)).thenReturn(pageCategoriesEntity);
        when(categoryMapperEntity.toCategoriesModel(categoriesEntity)).thenReturn(expectedCategories);

        //WHEN
        List<Category> result = categoryAdapter.getAllCategories(page, size, orderAsc);

        //THEN
        verify(categoryRepository, times(1)).findAll(pageable);
        verify(categoryMapperEntity, times(1)).toCategoriesModel(categoriesEntity);
    }
    @Test
    @DisplayName("success delete Category")
    void shouldDeleteCategory(){
        //GIVEN
        Long id= 1L;
        CategoryEntity category = new CategoryEntity(id, "Category Test","Description Unit Test", LocalDateTime.now(),null);
        //WHEN
        lenient().when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        categoryAdapter.deleteCategory(id);
        //THEN
        verify(categoryRepository, times(1)).deleteById(id);
    }
}
