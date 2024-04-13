package DagemovBackend.DaemovBackendEcommer.domain.api.usecase;

import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.IProductServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.DuplicateRecord;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.exception.UpdateError;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IProductPersistencePort;

import java.util.List;
import java.util.Optional;

public class UseCaseProduct implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public UseCaseProduct(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public Product addProduct(Product product) {
        if(isUniqueProduct(product)){
            throw new DuplicateRecord(Constants.DUPLICATE_RECORD + product.getName());
        }
        if(product.getStock() <0){
            throw new EmptyFile(Constants.ERROR_UPDATE + Constants.REQUIRED_INPUT + product.getStock() + " Stock");
        }
        return productPersistencePort.addProduct(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        if(id == null || id == 0){
            throw new EmptyFile(Constants.REQUIRED_INPUT + id +" : id");
        }
        Optional<Product> optionalProduct = productPersistencePort.getProductById(id);

        if(optionalProduct.isEmpty()){
            throw  new EmptyFile(Constants.NO_RECORD_FOUND + id);
        }

        return optionalProduct;
    }

    @Override
    public Optional<Product> getProductByName(String name) {

        if(name.isBlank() || name.isEmpty()){
            throw new EmptyFile(Constants.REQUIRED_INPUT + name );
        }
        Optional<Product> optionalProduct = productPersistencePort.getProductByName(name);

        if(optionalProduct.isEmpty()){
            throw  new EmptyFile(Constants.NO_RECORD_FOUND + "By the name : "+name);
        }
        return optionalProduct;
    }
    public boolean isUniqueProduct(Product product){
        return  productPersistencePort.getProductByName(product.getName()).isPresent();
    }
    public boolean findProduct(Long id){
        return productPersistencePort.getProductById(id).isPresent();
    }
    @Override
    public Product updated(Long id,Product product) {

        if(findProduct(id)){

            if(!isUniqueProduct(product))
            {
                if(product.getStock() <0){
                    throw new EmptyFile(Constants.ERROR_UPDATE + Constants.INVALID_INPUT + product.getStock() + " Stock have to up 0");
                }
                product.setId(id);
                return  productPersistencePort.updated(id,product);
            }else
            {
                throw new DuplicateRecord(Constants.DUPLICATE_RECORD_NAME + product.getName());
            }

        }
        else
        {
            throw new UpdateError(Constants.ERROR_UPDATE + product.getName());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if(findProduct(id)){
            productPersistencePort.deleteProduct(id);
        }else {
            throw new UpdateError(Constants.ERROR_DELETE + id);
        }
    }

    @Override
    public List<Product> getAllProducts(Integer page, Integer size, boolean orderAsc) {
        return productPersistencePort.getAllProducts(page,size,orderAsc);
    }
}