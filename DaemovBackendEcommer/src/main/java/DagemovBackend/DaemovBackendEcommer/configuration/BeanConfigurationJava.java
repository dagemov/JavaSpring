package DagemovBackend.DaemovBackendEcommer.configuration;

<<<<<<< HEAD
import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.CategoryAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.ICategoryMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.ICategoryRepository;
import DagemovBackend.DaemovBackendEcommer.domain.api.ICategoryServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.api.usecase.UseCaseCategory;
import DagemovBackend.DaemovBackendEcommer.domain.spi.ICategoryPersistencePort;
=======
import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.UserAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.IUserMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.IUserRepository;
import DagemovBackend.DaemovBackendEcommer.domain.api.IUserServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.api.usecase.UseCaseUser;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IUserPersistencePort;
>>>>>>> feature/users
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationJava {
<<<<<<< HEAD
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapperEntity categoryMapperEntity;
    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository,categoryMapperEntity);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new UseCaseCategory(categoryPersistencePort());
=======

    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return  new UserAdapter(userRepository,userMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UseCaseUser(userPersistencePort());
>>>>>>> feature/users
    }
}
