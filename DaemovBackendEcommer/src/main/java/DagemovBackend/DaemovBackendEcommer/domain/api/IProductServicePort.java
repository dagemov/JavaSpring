package DagemovBackend.DaemovBackendEcommer.domain.api;

import DagemovBackend.DaemovBackendEcommer.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductServicePort {
    Product addProduct(Product product);
    Optional<Product> getProductById(Long id);
    Optional<Product> getProductByName(String name);
    Product updated (Long id,Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts(Integer page, Integer size, boolean orderAsc);
}
