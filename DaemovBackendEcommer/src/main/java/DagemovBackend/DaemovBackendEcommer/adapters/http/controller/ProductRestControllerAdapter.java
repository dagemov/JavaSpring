package DagemovBackend.DaemovBackendEcommer.adapters.http.controller;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddProductDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.ProductResponseDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request.IProductRequestMapper;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response.IProductResponseMapper;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.IProductServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestControllerAdapter {

    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productResponseMapper;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductDTO addProductDTO){

        Product newProduct= productServicePort.addProduct(productRequestMapper.addProductRequest(addProductDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id ,@Valid @RequestBody AddProductDTO addProductDTO)
    {
        Product newProductUpdated = productServicePort.updated(id,productRequestMapper.addProductRequest(addProductDTO));
        return ResponseEntity.ok(newProductUpdated);
    }
    @GetMapping("/getProduct/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){

        Optional<Product> productOptional = productServicePort.getProductById(id);

        if(productOptional.isEmpty())
        {
            throw  new EmptyFile(Constants.NO_RECORD_FOUND + id);
        }
        return productOptional;
    }
    @GetMapping("/getProductByName/name")
    public Optional<Product> getProductByName(@RequestParam String name){

        Optional<Product> productOptional = productServicePort.getProductByName(name);

        if(productOptional.isEmpty())
        {
            throw  new EmptyFile(Constants.NO_RECORD_FOUND + name);
        }
        return productOptional;
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductResponseDTO>> getProductsListDTO(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true")boolean orderAsc
    ){
        List<Product> products = productServicePort.getAllProducts(page,size,orderAsc);
        List<ProductResponseDTO> productResponseDTOS = productResponseMapper.toProductResponseDtoList(products);

        return ResponseEntity.ok(productResponseDTOS);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id){
        productServicePort.deleteProduct(id);
    }
}
