package DagemovBackend.DaemovBackendEcommer.adapters.database.mapper;


import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMapperEntity {
    @Mappings({
            @Mapping(target = "firstName",source = "firstName"),
            @Mapping(target = "lastName",source = "lastName"),
            @Mapping(target = "email",source = "email"),
            @Mapping(source = "address",target = "address"),
            @Mapping(source = "phoneNumber",target = "phoneNumber"),
            @Mapping(source = "password",target = "password"),
            @Mapping(source = "userType",target = "userType"),
            @Mapping(source = "createdDate",target = "createdDate"),
            @Mapping(source = "updatedDate",target = "updatedDate")
    })
    User toModel(UserEntity userEntity);
    UserEntity toUserEntity(User user);
    List<User> toModelList(List<UserEntity> userEntities);
}
