package DagemovBackend.DaemovBackendEcommer.configuration;

import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.CategoryAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.ICategoryMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.ICategoryRepository;
import DagemovBackend.DaemovBackendEcommer.domain.api.ICategoryServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.api.usecase.UseCaseCategory;
import DagemovBackend.DaemovBackendEcommer.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationJava {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapperEntity categoryMapperEntity;
    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository,categoryMapperEntity);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new UseCaseCategory(categoryPersistencePort());
    }
}
