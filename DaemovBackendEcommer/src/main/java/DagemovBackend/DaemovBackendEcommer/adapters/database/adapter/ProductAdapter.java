package DagemovBackend.DaemovBackendEcommer.adapters.database.adapter;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.ProductEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.IProductMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.IProductRepository;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductMapperEntity productMapperEntity;

    @Override
    public Product addProduct(Product product) {
        productRepository.save(productMapperEntity.toProductEntity(product));
        return  product;
    }

    @Override
    public Optional<Product> getProductById(Long id) {

        Optional<ProductEntity> productOptional = productRepository.findById(id);
        return  productOptional.map(productMapperEntity :: toModel);
    }
    @Override
    public Optional<Product> getProductByName(String name) {
        Optional<ProductEntity> productOptional = productRepository.findByName(name);
        return  productOptional.map(productMapperEntity :: toModel);
    }



    public boolean isUniqueProduct(Product product){
        return  productRepository.findByName(product.getName()).isPresent();
    }
    public boolean findProduct(Product product){
        return productRepository.findById(product.getId()).isPresent();
    }
    @Override
    public Product updated(Long id,Product product) {
        productRepository.save(productMapperEntity.toProductEntity(product));
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts(Integer page, Integer size, boolean orderAsc) {

        Sort sort = orderAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        List<ProductEntity> productList = productRepository.findAll(pageable).getContent();

        if(productList.isEmpty()){
            throw  new EmptyFile(Constants.NO_RECORD_FOUND);
        }

        return productMapperEntity.toModelProductList(productList);
    }
}
