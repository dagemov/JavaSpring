package DagemovBackend.DaemovBackendEcommer.adapters.database.adapter;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.IUserMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.IUserRepository;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.exception.DuplicateRecord;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapperEntity;

    @Override
    public User addUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateRecord(Constants.DUPLICATE_RECORD + user.getEmail());
        }
        userRepository.save(userMapperEntity.toUserEntity(user));
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if(email.isEmpty() || email.isBlank()){
            throw  new EmptyFile(Constants.NO_RECORD_FOUND);
        }
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityOptional.map(userMapperEntity::toModel);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            return userEntityOptional.map(userMapperEntity::toModel);
        }
        return Optional.empty();
    }

    @Override
    public User updateUser(Long id, User user) {

        userRepository.save(userMapperEntity.toUserEntity(user));
        return user;

    }

    @Override
    public void DeleteUser(Long id) {

        userRepository.deleteById(id);

    }

    @Override
    public List<User> getAllUser(Integer page, Integer size, boolean orderAsc) {
        Sort sort = orderAsc ? Sort.by("email").ascending() : Sort.by("email").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        List<UserEntity> userEntities= userRepository.findAll(pageable).getContent();

        if(userEntities.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND);
        }
        return userMapperEntity.toModelList(userEntities);
    }
}
