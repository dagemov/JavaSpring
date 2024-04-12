package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import DagemovBackend.DaemovBackendEcommer.configuration.Constants;
import DagemovBackend.DaemovBackendEcommer.domain.model.UserType;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserDTO {
    @NotBlank(message = Constants.REQUIRED_INPUT+" First Name")
    @Size(max = 50,message = Constants.MAX_LENGTH_STRING + "50 characters")
    private String firstName;
    @Size(max = 50,message = Constants.MAX_LENGTH_STRING+ "50 characters")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = Constants.REQUIRED_INPUT+" Email")
    @Size(max = 150,message = Constants.MAX_LENGTH_STRING+ "150 characters")
    private String email;
    private String address;
    private String phoneNumber;
    @NotBlank(message = Constants.REQUIRED_INPUT+" Password")
    @Size(max = 150,message = Constants.MAX_LENGTH_STRING+ "150 characters")
    private String password;
    @NotBlank(message = Constants.REQUIRED_INPUT+" Password")
    @Size(max = 150,message = Constants.MAX_LENGTH_STRING+ "150 characters")
    private String confirmPassword;
    private UserType userType;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
