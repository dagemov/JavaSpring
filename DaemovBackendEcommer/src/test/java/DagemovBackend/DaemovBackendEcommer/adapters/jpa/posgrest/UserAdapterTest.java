package DagemovBackend.DaemovBackendEcommer.adapters.jpa.posgrest;

import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.UserAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.IUserMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.IUserRepository;
import DagemovBackend.DaemovBackendEcommer.dataTest.TestUserData;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.model.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserAdapterTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IUserMapperEntity userMapperEntity;
    @InjectMocks
    private UserAdapter userAdapter;
    private List<User> users= TestUserData.createUser();
    @Test
    @DisplayName("Add user from adapter")
    void shouldAddUser(){

        //GIVEN
        User user = users.get(0);
        //WHEN
        lenient().when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        userAdapter.addUser(user);
        //THEN
        verify(userRepository,times(1)).save(any());
    }
    @Test
    @DisplayName("Update user from adapter")
    void shouldUpdateUser(){
        Long id = 1L;
        //GIVEN
        User user = users.get(1);
        user.setId(id);
        //WHEN
        lenient().when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        userAdapter.updateUser(id,user);
        //THEN
        verify(userRepository,times(1)).save(any());
    }
    @Test
    @DisplayName("Get user by email from adapter")
    void shouldGetByEmail()
    {

        //GIVEN
        UserEntity userEntity = new UserEntity(10L, "Sebastian", "Martinez", "user1@test.com", "password1", "123 Test St", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.Admin);
        User user = users.get(0);
        //WHEN
        lenient().when(userRepository.findByEmail(any())).thenReturn(Optional.of(userEntity));
        Optional<User> response =userAdapter.getUserByEmail(user.getEmail());

        //THEN
        verify(userRepository,times(1)).findByEmail(any());
    }
    @Test
    @DisplayName("Get user by id from adapter")
    void shouldGetById(){
        //GIVEN
        UserEntity userEntity = new UserEntity(10L, "Sebastian", "Martinez", "user1@test.com", "password1", "123 Test St", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.Admin);
        User user = users.get(0);
        user.setId(10L);
        //WHEN
        lenient().when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        Optional<User> response =userAdapter.getUserById(10L);
        //THEN
        verify(userRepository,times(1)).findById(any());
    }
    @Test
    @DisplayName("Get all users from adapter")
    void shouldGetAllUsers()
    {
        //GIVEN
        Integer page = 0;
        Integer size = 10;
        boolean orderAsc = true;

        List<UserEntity> usersEntity = Arrays.asList(
                new UserEntity(
                        10L, "Sebastian", "Martinez", "user1@test.com",
                        "password1", "123 Test St", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.Admin
                ));
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderAsc ? "email" : "email").ascending());
        Page<UserEntity> userEntityList = new PageImpl<>(usersEntity, pageable, size);

        //WHEN

        when(userRepository.findAll(pageable)).thenReturn(userEntityList);
        when(userMapperEntity.toModelList(usersEntity)).thenReturn(users);
        List<User> response = userAdapter.getAllUser(0,10,true);

        //THEN
        verify(userRepository,times(1)).findAll(pageable);
        verify(userMapperEntity,times(1)).toModelList(usersEntity);
    }
    @Test
    @DisplayName("Delete user from adapter")
    void shouldDeleteUser(){
        // GIVEN
        Long id = 1L;
        User user= users.get(0);
        // WHEN
        lenient().when(userRepository.findById(id)).thenReturn(Optional.empty());
        userAdapter.DeleteUser(id);

        // THEN
        verify(userRepository).deleteById(id);
    }
}
