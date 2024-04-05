package DagemovBackend.DaemovBackendEcommer.adapters.http.controller;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddCategoryDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.CategoryResponseDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request.ICategoryRequestMapper;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response.ICategoryResponseMapper;
import DagemovBackend.DaemovBackendEcommer.dataTest.TestCategoryData;
import DagemovBackend.DaemovBackendEcommer.domain.api.ICategoryServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryRestControllerAdapterTest {
    @Mock
    private ICategoryServicePort categoryServicePort;
    @Mock
    private ICategoryResponseMapper categoryResponseMapper;
    @Mock
    private ICategoryRequestMapper categoryRequestMapper;
    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    private List<Category> categories= TestCategoryData.createCategory();


    @Test
    @DisplayName("Test addCategory method")
    void testAddCategory() {
        //GIVEN
        AddCategoryDTO addCategoryDTO = new AddCategoryDTO("Category Test", "Description Unit Test", LocalDateTime.now(), null);
        Category category = categories.get(0);
        //WHEN
        when(categoryServicePort.addCategory(any())).thenReturn(category);
        ResponseEntity<Category> response = categoryRestControllerAdapter.addCategory(addCategoryDTO);
        //THEN
        verify(categoryServicePort).addCategory(categoryRequestMapper.addCategory(addCategoryDTO));

    }

    @Test
    @DisplayName("Test updateCategory method")
    void testUpdateCategory() {
        //GIVEN

        Long id = 20L;
        AddCategoryDTO addCategoryDTO = new AddCategoryDTO("Tag Category Test", "Tag Description Unit Test", LocalDateTime.now(), null);
        Category updatedCategory = categories.get(0);
        updatedCategory.setId(id);

        //WHEN

        when(categoryServicePort.updateCategory(id, categoryRequestMapper.addCategory(addCategoryDTO))).thenReturn(updatedCategory);
        ResponseEntity<Category> response = categoryRestControllerAdapter.updateCategory(id, addCategoryDTO);

        //THEN

        verify(categoryServicePort,times(1)).updateCategory(id, categoryRequestMapper.addCategory(addCategoryDTO));
    }

    @Test
    @DisplayName("Test getCategoryById method")
    void testGetCategoryById() {
        //GIVEN

        Long id = 30L;
        Category category = categories.get(0);
        category.setId(id);

        //WHEN

        when(categoryServicePort.getCategoryById(id)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryRestControllerAdapter.getCategoryById(id);

        //THEN

        verify(categoryServicePort,times(1)).getCategoryById(id);
    }

    @Test
    @DisplayName("Test getCategoryByName method")
    void testGetCategoryByName() {
        //GIVEN

        String name = "Cream Category Test";
        Category category = new Category(40L, "Cream Category Test", "Cream Description Unit Test", LocalDateTime.now(), null);

        //WHEN

        when(categoryServicePort.getCategoryByName(name)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryRestControllerAdapter.getCategoryByName(name);

        //THEN

        verify(categoryServicePort,times(1)).getCategoryByName(name);
    }

    @Test
    @DisplayName("Test getAllCategories method")
    void testGetAllCategories() {
        //GIVEN
        List<Category> categories = TestCategoryData.createCategory();
        List<CategoryResponseDTO> categoryResponseDTOS = categories.stream()
                .map(categoryResponseMapper::toResponseCategoryDTO)
                .collect(Collectors.toList());
        //WHEN
        when(categoryServicePort.getAllCategories(0, 10, true)).thenReturn(categories);
        when(categoryResponseMapper.toCategoriesDTOS(categories)).thenReturn(categoryResponseDTOS);
        ResponseEntity<List<CategoryResponseDTO>> response = categoryRestControllerAdapter.getAllCategories(0, 10, true);
        //THEN
        verify(categoryServicePort,times(1)).getAllCategories(0, 10, true);
        verify(categoryResponseMapper,times(1)).toCategoriesDTOS(categories);

    }
    @Test
    @DisplayName("Test delete Category")
    void shouldDeleteCategory(){
        //GIVEN
        Long id= 1L;
        //WHEN
        lenient().when(categoryServicePort.getCategoryById(id)).thenReturn(Optional.of(categories.get(0)));
        categoryRestControllerAdapter.deleteCategory(id);
        //THEN
        verify(categoryServicePort, times(1)).deleteCategory(id);
    }
}
