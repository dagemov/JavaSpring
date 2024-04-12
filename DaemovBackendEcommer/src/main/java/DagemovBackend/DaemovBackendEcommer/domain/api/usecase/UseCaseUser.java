package DagemovBackend.DaemovBackendEcommer.domain.api.usecase;

import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.IUserServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.DuplicateString;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IUserPersistencePort;

import java.util.List;
import java.util.Optional;

public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UseCaseUser(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public User addUser(User user) {
        if(isUniqueEmail(user)){
            throw  new DuplicateString(Constants.DUPLICATE_RECORD + user.getEmail());
        }
        return  userPersistencePort.addUser(user);
    }
    public boolean isUniqueEmail(User user) {
        return userPersistencePort.getUserByEmail(user.getEmail()).isPresent();
    }
    public boolean findUserById(Long id) {
        return userPersistencePort.getUserById(id).isPresent();
    }

    @Override
    public Optional<User> getByEmail(String email) {

        if(email.isBlank() || email.isEmpty()){
            throw  new EmptyFile(Constants.NO_RECORD_FOUND+ email);
        }

        Optional<User> userSelected = userPersistencePort.getUserByEmail(email);

        if(userSelected.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND + email);
        }
        return userSelected;
    }
    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> userSelected = userPersistencePort.getUserById(id);

        if(userSelected.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND + id);
        }
        return userSelected;
    }

    @Override
    public User updateUser(Long id, User user) {
        if(findUserById(id)){
            if(!isUniqueEmail(user))
            {
                if(user.getEmail().isEmpty() || user.getEmail().isBlank())
                {
                    throw  new EmptyFile(Constants.REQUIRED_INPUT + "Email");
                }
                user.setId(id);
                return userPersistencePort.updateUser(id,user);
            }else
            {
                throw new DuplicateString(Constants.DUPLICATE_RECORD + user.getEmail());
            }
        }else{
            throw new EmptyFile(Constants.NO_RECORD_FOUND + id);

        }
    }

    @Override
    public void DeleteUser(Long id) {
        if(findUserById(id))
        {
            userPersistencePort.DeleteUser(id);
        }else
        {
            throw new EmptyFile(Constants.NO_RECORD_FOUND + id);
        }
    }


    @Override
    public List<User> getAllUsers(Integer page, Integer size, boolean orderAsc) {
        return userPersistencePort.getAllUser(page,size,orderAsc);
    }
}
