package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.ProductResponseDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductResponseMapper {
    ProductResponseDTO toProductResponseDTO(Product product);
    List<ProductResponseDTO> toProductResponseDtoList(List<Product>products);
}
