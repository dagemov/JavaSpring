package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request;


import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddProductDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IProductRequestMapper {
    @Mappings({
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "description",target = "description"),
            @Mapping(source = "urlImage",target = "urlImage"),
            @Mapping(source = "stock",target = "stock"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "userEntity.id",target = "userId"),
            @Mapping(source = "categoryEntity.id",target = "categoryId"),
            @Mapping(source = "createdDate",target = "createdDate"),
            @Mapping(source = "updatedDate",target = "updatedDate")
    })
    Product addProductRequest(AddProductDTO addProductDTO);
}
