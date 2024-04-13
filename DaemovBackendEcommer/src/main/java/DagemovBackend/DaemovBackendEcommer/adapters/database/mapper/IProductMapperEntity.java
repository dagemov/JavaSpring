package DagemovBackend.DaemovBackendEcommer.adapters.database.mapper;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.ProductEntity;
import DagemovBackend.DaemovBackendEcommer.domain.model.Product;
import org.hibernate.annotations.Comment;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IProductMapperEntity {
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
    Product toModel(ProductEntity productEntity);
    @InheritInverseConfiguration
    ProductEntity toProductEntity(Product product);
    List<Product> toModelProductList(List<ProductEntity> productEntityList);
}
