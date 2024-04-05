package DagemovBackend.DaemovBackendEcommer.adapters.http.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CategoryResponseDTO {
    private final Long id;
    private final String name;
    private final String Description;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}
