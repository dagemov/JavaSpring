package DagemovBackend.DaemovBackendEcommer.domain.spi;

import DagemovBackend.DaemovBackendEcommer.domain.model.User;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public interface IUserPersistencePort {
    User addUser(User user);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User user);
    void DeleteUser(Long id);
    List<User> getAllUser(Integer page, Integer size, boolean orderAsc);
}
