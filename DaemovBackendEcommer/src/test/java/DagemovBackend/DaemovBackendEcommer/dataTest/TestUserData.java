package DagemovBackend.DaemovBackendEcommer.dataTest;

import DagemovBackend.DaemovBackendEcommer.domain.model.User;
import DagemovBackend.DaemovBackendEcommer.domain.model.UserType;

import java.time.LocalDateTime;
import java.util.List;

public class TestUserData {
    public static List<User> createUser() {
        User user1 = new User(10L, "Sebastian", "Martinez", "user1@test.com", "password1", "123 Test St", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.Admin);
        User user2 = new User(20L, "Carolina", "Echeverry", "Carito@test.com", "password2", "456 Example Ave", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.Admin);
        User user3 = new User(30L, "John", "Doe", "John@test.com", "password3", "789 Sample Rd", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.User);
        User user = new User(40L, "Jane", "Doe", "Jane@test.com", "password4", "456 Sample Rd", "1234567890", LocalDateTime.now(), LocalDateTime.now(), UserType.User);
        List<User> users = List.of(user,user1,user2,user3);
        return users;
    }
}
