package DagemovBackend.DaemovBackendEcommer.adapters.http.controller;

import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request.AddUserDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response.UserResponseDTO;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.request.IUserRequestMapper;
import DagemovBackend.DaemovBackendEcommer.adapters.http.mapper.response.IUserResponseMapper;
import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.api.IUserServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.exception.EmptyFile;
import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestControllerAdapter {

    private final IUserServicePort userServicePort;
    private final IUserResponseMapper userResponseMapper;
    private final IUserRequestMapper userRequestMapper;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody AddUserDTO addUserDTO){

        User newUser = userServicePort.addUser(userRequestMapper.addRequestUser(addUserDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @GetMapping("/getUser/email")
    public Optional<User> getUserByEmail(@RequestParam String email){

        if(email.isBlank() || email.isEmpty())
        {
            throw  new EmptyFile(Constants.NO_RECORD_FOUND+"\nMake sure that u are input the correct email");
        }

        Optional<User> userOptional = userServicePort.getByEmail(email);

        if(userOptional.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND+"\n"+email);
        }

        return userOptional;
    }
    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable Long id)
    {
        Optional<User> userOptional = userServicePort.getUserById(id);

        if(userOptional.isEmpty()){
            throw new EmptyFile(Constants.NO_RECORD_FOUND+"\n"+id);
        }
        return userOptional;
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody AddUserDTO addUserDTO)
    {
        User updateUser = userServicePort.updateUser(id,userRequestMapper.addRequestUser(addUserDTO));

        return  ResponseEntity.ok(updateUser);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true")boolean orderAsc
    )
    {
        List<User> users = userServicePort.getAllUsers(page,size,orderAsc);
        List<UserResponseDTO> userResponseDTOS = userResponseMapper.toUserResponseList(users);

        return ResponseEntity.ok(userResponseDTOS);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        if(userServicePort.getUserById(id).isPresent())
        {
            userServicePort.DeleteUser(id);
        }else
        {
            throw new EmptyFile(Constants.NO_RECORD_FOUND+"\n"+id);
        }
    }
}
