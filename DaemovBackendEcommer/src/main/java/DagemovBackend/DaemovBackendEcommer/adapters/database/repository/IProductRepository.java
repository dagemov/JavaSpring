package DagemovBackend.DaemovBackendEcommer.adapters.database.repository;

import DagemovBackend.DaemovBackendEcommer.adapters.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository  extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findById(Long id);
    Optional<ProductEntity> findByName(String name);
    Page<ProductEntity> findAll(Pageable pageable);
}
