package DagemovBackend.DaemovBackendEcommer.domain.api.usecase;

import DagemovBackend.DaemovBackendEcommer.dataTest.TestUserData;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUserCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @InjectMocks
    private UseCaseUser useCaseUser;
    private List<User> users= TestUserData.createUser();

    @Test
    @DisplayName("Test successful to add user")
    void shouldAddUser(){
        //GIVEN
        User user = users.get(0);
        //WHEN
        when(userPersistencePort.addUser(user)).thenReturn(user);
        useCaseUser.addUser(user);
        //THEN
        verify(userPersistencePort).addUser(user);
    }
    @Test
    @DisplayName("Test successful to update user")
    void shouldUpdateUser(){
        // GIVEN
        Long id = 1L;
        User user = users.get(0);
        when(userPersistencePort.getUserById(id)).thenReturn(Optional.of(user));

        // WHEN
        useCaseUser.updateUser(id, user);

        // THEN
        verify(userPersistencePort).updateUser(id, user);
    }
    @Test
    @DisplayName("Test successful to get user by email")
    void shouldGetUserByEmail(){
        //GIVEN
        User user = users.get(0);
        //WHEN
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        useCaseUser.getByEmail(user.getEmail());
        //THEN
        verify(userPersistencePort).getUserByEmail(user.getEmail());
    }
    @Test
    @DisplayName("Test successful to get user by id")
    void shouldGetUserById(){
        // GIVEN
        Long id = 1L;
        User user = users.get(0);
        when(userPersistencePort.getUserById(id)).thenReturn(Optional.of(user));

        // WHEN
        Optional<User> result = useCaseUser.getUserById(id);

        // THEN
        verify(userPersistencePort).getUserById(id);
    }
    @Test
    @DisplayName("Test successful to get all users")
    void shouldGetAllUsers(){
        //WHEN
        when(userPersistencePort.getAllUser(0,10,true)).thenReturn(users);
        useCaseUser.getAllUsers(0,10,true);
        //THEN
        verify(userPersistencePort).getAllUser(0,10,true);
    }
    @Test
    @DisplayName("Test successful to delete user")
    void shouldDeleteUser() {
        // GIVEN
        Long id = 1L;
        User user= users.get(0);
        when(userPersistencePort.getUserById(id)).thenReturn(Optional.of(user));

        // WHEN
        useCaseUser.DeleteUser(id);

        // THEN
        verify(userPersistencePort).DeleteUser(id);
    }

}
