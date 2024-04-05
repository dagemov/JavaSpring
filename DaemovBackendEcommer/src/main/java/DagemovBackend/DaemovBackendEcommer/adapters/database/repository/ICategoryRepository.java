package DagemovBackend.DaemovBackendEcommer.adapters.database.repository;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findById(Long id);
    Optional<CategoryEntity> findByName(String name);
    Page<CategoryEntity> findAll(Pageable pageable);
}
