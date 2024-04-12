package DagemovBackend.DaemovBackendEcommer.adapters.http.controller;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddUserDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.UserResponseDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request.IUserRequestMapper;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response.IUserResponseMapper;
import DagemovBackend.DaemovBackendEcommer.dataTest.TestUserData;
import DagemovBackend.DaemovBackendEcommer.domain.api.IUserServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.model.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerAdapterTest {
    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private IUserResponseMapper userResponseMapper;
    @Mock
    private IUserRequestMapper userRequestMapper;
    private List<User> users= TestUserData.createUser();
    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;
    @Test
    @DisplayName("Test add user adapter")

    void shouldAddUser(){
        //GIVEN
        User user = users.get(0);
        AddUserDTO userDTO = new AddUserDTO( "Sebastian", "Martinez", "user1@test.com", "123 Test St", "2034567890", "password1","password1", UserType.Admin, LocalDateTime.now(), LocalDateTime.now());
        //WHEN
        lenient().when(userServicePort.addUser(any())).thenReturn(user);
        ResponseEntity<User> response = userRestControllerAdapter.addUser(userDTO);
        //THEN
        verify(userServicePort,times(1)).addUser(userRequestMapper.addRequestUser(userDTO));
    }
    @Test
    @DisplayName("Test get user by email adapter")
    void shouldGetUserByEmail(){
        //GIVEN
        User user = users.get(0);
        AddUserDTO userDTO = new AddUserDTO( "Sebastian", "Martinez", "user1@test.com", "123 Test St", "2034567890", "password1","password1", UserType.Admin, LocalDateTime.now(), LocalDateTime.now());
        //WHEN
        lenient().when(userServicePort.getByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Optional<User> response = userRestControllerAdapter.getUserByEmail(user.getEmail());
        //THEN
        verify(userServicePort).getByEmail(user.getEmail());
    }
    @Test
    @DisplayName("Test get user by id adapter")
    public void testGetUserById() {
        // GIVEN
        Long userId = users.get(0).getId();
        // WHEN
        when(userServicePort.getUserById(userId)).thenReturn(Optional.of(users.get(0)));
        Optional<User> result = userRestControllerAdapter.getUserById(userId);

        // THEN
        verify(userServicePort).getUserById(userId);
    }
    @Test
    @DisplayName("Test update user adapter")
    void shouldUpdateUser(){
        //GIVEN
        Long id = 20L;
        User user = users.get(0);
        user.setId(id);
        AddUserDTO userDTO = new AddUserDTO( "Sebastian", "Martinez", "user1@test.com", "123 Test St", "2034567890", "password1","password1", UserType.Admin, LocalDateTime.now(), LocalDateTime.now());
        //WHEN
        lenient().when(userServicePort.updateUser(any(),any())).thenReturn(user);
        ResponseEntity<User> response = userRestControllerAdapter.updateUser(user.getId(),userDTO);
        //THEN
        verify(userServicePort,times(1)).updateUser(user.getId(),userRequestMapper.addRequestUser(userDTO));
    }
    @Test
    @DisplayName("Test get all users adapter")
    void shouldGetAllUsers(){
        //GIVEN

        List<User> users= TestUserData.createUser();
        List<UserResponseDTO> userDTOS = users.stream()
                .map(userResponseMapper::toUserResponse)
                .collect(Collectors.toList());
        //WHEN
        when(userServicePort.getAllUsers(0,10,true)).thenReturn(users);
        when(userResponseMapper.toUserResponseList(users)).thenReturn(userDTOS);
        ResponseEntity<List<UserResponseDTO>> response = userRestControllerAdapter.getAllUser(0,10,true);
        //THEN
        verify(userServicePort,times(1)).getAllUsers(0,10,true);
        verify(userResponseMapper,times(1)).toUserResponseList(users);
    }
    @Test
    @DisplayName("Test delete user adapter")
    public void testDeleteUser() {
        // GIVEN

        Long userId = users.get(0).getId();

        // WHEN

        when(userServicePort.getUserById(userId)).thenReturn(Optional.of(users.get(0)));
        userRestControllerAdapter.deleteUser(userId);

        // THEN
        verify(userServicePort, times(1)).DeleteUser(userId);
    }
}
