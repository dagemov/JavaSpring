package DagemovBackend.DaemovBackendEcommer.adapters.database.repository;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findAll(Pageable pageable);
}