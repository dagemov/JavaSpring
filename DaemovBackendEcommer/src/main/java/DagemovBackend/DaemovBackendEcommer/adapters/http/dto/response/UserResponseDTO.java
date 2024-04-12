package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}
