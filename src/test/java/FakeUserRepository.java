import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class FakeUserRepository implements UserRepository {

    private boolean usernameExists = false;
    private User storedUser;

    public void setUsernameExists(boolean exists) {
        this.usernameExists = exists;
    }

    @Override
    public boolean existsByUsername(String username) {
        return usernameExists;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(User user) {
        this.storedUser = user;
    }

    @Override
    public User findById(int id) {
        return storedUser;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        if ("admin".equals(username) && "1234".equals(password)) {

            return new User.UserBuilder()
                    .userId(1)
                    .username("admin")
                    .password("1234")
                    .fullName("Admin")
                    .contactNo("077")
                    .address("Colombo")
                    .role("ADMIN")
                    .build();
        }

        return null;
    }

    @Override
    public void update(User user) {
        this.storedUser = user;
    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public void resetPassword(int userId, String newPassword) {

    }

    @Override
    public boolean usernameExists(String username) {

        if (storedUser != null && storedUser.getUsername().equalsIgnoreCase(username)) {
            return true;
        }

        return false;
    }
}