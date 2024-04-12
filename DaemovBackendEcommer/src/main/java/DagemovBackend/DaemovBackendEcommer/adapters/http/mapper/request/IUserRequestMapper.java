package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddUserDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
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
    User addRequestUser(@Valid AddUserDTO addUserDTO);
}