package DagemovBackend.DaemovBackendEcommer.domain.api.usecase;

import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.ICategoryServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.DuplicateRecord;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.exception.RequiredInput;
import DagemovBackend.DaemovBackendEcommer.domain.model.Category;
import DagemovBackend.DaemovBackendEcommer.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.Optional;

public class UseCaseCategory implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public UseCaseCategory(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category addCategory(Category category) {
        if(category.getName().isEmpty() || category.getName().isBlank()){
            throw new RequiredInput(Constants.REQUIRED_INPUT + "Category name");
        }
        if(isUniqueCategoryName(category))
        {
            throw  new DuplicateRecord(Constants.DUPLICATE_RECORD_NAME + category.getName());
        }
        categoryPersistencePort.addCategory(category);

        return category;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {

        if(id==0 || id == null)
        {
            throw new EmptyFile(Constants.REQUIRED_INPUT + "id to found it");
        }

        Optional<Category> categoryOptional = categoryPersistencePort.getCategoryById(id);

        if(categoryOptional.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND+"id:"+id);
        }

        return categoryOptional;
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {

        if(name.isEmpty() || name.isBlank())
        {
            throw  new EmptyFile(Constants.REQUIRED_INPUT + "Category Name to found it");
        }

        Optional<Category> categoryOptional = categoryPersistencePort.getCategoryByName(name);

        if(categoryOptional.isEmpty())
        {
            throw new EmptyFile(Constants.NO_RECORD_FOUND+ name);
        }

        return categoryOptional;
    }
    public boolean isUniqueCategoryName(Category category){
        return categoryPersistencePort.getCategoryByName(category.getName()).isPresent();
    }
    public boolean findCategoryId(Long id){
        return  categoryPersistencePort.getCategoryById(id).isPresent();
    }
    @Override
    public Category updateCategory(Long id, Category category) {
        if(findCategoryId(id))
        {
            if(!isUniqueCategoryName(category))
            {
                if(category.getName().isEmpty() || category.getName().isBlank())
                {
                    throw new RequiredInput(Constants.REQUIRED_INPUT + "Category name");
                }
                category.setId(id);
                return categoryPersistencePort.updateCategory(id,category);
            }else
            {
                throw new DuplicateRecord(Constants.DUPLICATE_RECORD_NAME + category.getName());
            }
        }else
        {
            throw new EmptyFile(Constants.NO_RECORD_FOUND + "id:"+id+" error to updated with that id NOT FOUND");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if(findCategoryId(id)){
            categoryPersistencePort.deleteCategory(id);
        }else{

            throw new EmptyFile(Constants.NO_RECORD_FOUND + "id:"+id+" error to delete with that id NOT FOUND");
        }
    }



    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryPersistencePort.getAllCategories(page,size,orderAsc);
    }
}
