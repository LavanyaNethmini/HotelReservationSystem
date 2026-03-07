import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void shouldRegisterUserSuccessfully() {

        FakeUserRepository fakeRepo = new FakeUserRepository();
        fakeRepo.setUsernameExists(false);

        UserService service = new UserServiceImpl(fakeRepo);

        User user = new User.UserBuilder()
                .userId(0)
                .username("john")
                .password("1234")
                .fullName("John")
                .contactNo("077")
                .address("Colombo")
                .role("ADMIN")
                .build();

        service.registerUser(user);

        assertEquals("john", fakeRepo.findById(0).getUsername());
    }

    @Test
    void shouldThrowExceptionWhenUsernameExists() {

        FakeUserRepository fakeRepo = new FakeUserRepository();
        fakeRepo.setUsernameExists(true);

        UserService service = new UserServiceImpl(fakeRepo);

        User user = new User.UserBuilder()
                .userId(0)
                .username("john")
                .password("1234")
                .fullName("John")
                .contactNo("077")
                .address("Colombo")
                .role("ADMIN")
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                service.registerUser(user));
    }

    @Test
    void shouldReturnUserWhenCredentialsValid() {

        UserService service = new UserServiceImpl(new FakeUserRepository());

        User user = service.login("admin", "1234");

        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void shouldReturnNullWhenCredentialsInvalid() {

        UserService service = new UserServiceImpl(new FakeUserRepository());

        User user = service.login("wrong", "wrong");

        assertNull(user);
    }

    @Test
    void shouldPreservePasswordWhenUpdatingUser() {

        FakeUserRepository fakeRepo = new FakeUserRepository();

        User existing = new User.UserBuilder()
                .userId(1)
                .username("john")
                .password("secret123")
                .fullName("John")
                .contactNo("077")
                .address("Colombo")
                .role("STAFF")
                .build();
        fakeRepo.save(existing);

        UserService service = new UserServiceImpl(fakeRepo);

        User updated = new User.UserBuilder()
                .userId(1)
                .username("john")          // REQUIRED
                .password("secret123")     // REQUIRED (even if ignored later)
                .fullName("John Updated")
                .contactNo("071")
                .address("Kandy")
                .role("ADMIIN")
                .build();

        service.updateUser(updated);

        User result = fakeRepo.findById(1);

        assertEquals("secret123", result.getPassword());
    }



    }
