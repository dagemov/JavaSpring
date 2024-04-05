package DagemovBackend.DaemovBackendEcommer.adapters.http.controller;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddCategoryDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.CategoryResponseDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request.ICategoryRequestMapper;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response.ICategoryResponseMapper;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.ICategoryServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor

public class CategoryRestControllerAdapter {
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO)
    {
        Category newCategory = categoryServicePort.addCategory(categoryRequestMapper.addCategory(addCategoryDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
    @PutMapping("/updatedCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@Valid @RequestBody AddCategoryDTO addCategoryDTO)
    {
        Category updateCategory = categoryServicePort.updateCategory(id,categoryRequestMapper.addCategory(addCategoryDTO));

        return  ResponseEntity.ok(updateCategory);
    }
    @GetMapping("/getCategoryById/{id}")
    public Optional<Category> getCategoryById(@PathVariable  Long id)
    {
        Optional<Category> categoryOptional = categoryServicePort.getCategoryById(id);

        if(categoryOptional.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND+" with this id to updated : "+id);
        }

        return categoryOptional;
    }
    @GetMapping("/getCategoryByName/name")
    public Optional<Category> getCategoryByName(@RequestParam String name)
    {
        Optional<Category> categoryOptional = categoryServicePort.getCategoryByName(name);

        if(categoryOptional.isEmpty()){throw new EmptyFile(Constants.NO_RECORD_FOUND+" with this name : "+name);}

        return categoryOptional;
    }
    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true")boolean orderAsc
    ){

        List<Category> categories = categoryServicePort.getAllCategories(page,size,orderAsc);
        List<CategoryResponseDTO> categoryResponseDTOS = categoryResponseMapper.toCategoriesDTOS(categories);

        return ResponseEntity.ok(categoryResponseDTOS);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
        categoryServicePort.deleteCategory(id);
    }
}