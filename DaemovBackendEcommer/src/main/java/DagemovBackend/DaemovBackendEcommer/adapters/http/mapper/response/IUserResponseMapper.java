package DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.UserResponseDTO;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponseDTO toUserResponse(User user);
    List<UserResponseDTO> toUserResponseList(List<User> userList);
}
