package DagemovBackend.DaemovBackendEcommer.domain.api;

import DagemovBackend.DaemovBackendEcommer.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserServicePort {
    User addUser(User user);
    Optional<User> getByEmail(String email);
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User user);
    void DeleteUser(Long id);
    List<User> getAllUsers(Integer page, Integer size, boolean orderAsc);
}
