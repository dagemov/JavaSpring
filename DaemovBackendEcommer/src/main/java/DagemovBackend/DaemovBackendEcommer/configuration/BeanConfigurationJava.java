package DagemovBackend.DaemovBackendEcommer.configuration;

import DagemovBackend.DaemovBackendEcommer.adapters.database.adapter.UserAdapter;
import DagemovBackend.DaemovBackendEcommer.adapters.database.mapper.IUserMapperEntity;
import DagemovBackend.DaemovBackendEcommer.adapters.database.repository.IUserRepository;
import DagemovBackend.DaemovBackendEcommer.domain.api.IUserServicePort;
import DagemovBackend.DaemovBackendEcommer.domain.api.usecase.UseCaseUser;
import DagemovBackend.DaemovBackendEcommer.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationJava {

    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return  new UserAdapter(userRepository,userMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UseCaseUser(userPersistencePort());
    }
}
