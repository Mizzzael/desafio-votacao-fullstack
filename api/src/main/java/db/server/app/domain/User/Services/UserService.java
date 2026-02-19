package db.server.app.domain.User.Services;

import db.server.app.commons.Enums.UserLevel;
import db.server.app.domain.User.Interfaces.IPassEncoder;
import db.server.app.domain.User.DTO.UserDTO;
import db.server.app.domain.User.Model.User;
import db.server.app.domain.User.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final IPassEncoder passEncoder;

    public UserService(UserRepository userRepository, IPassEncoder passEncoder) {
        this.userRepository = userRepository;
        this.passEncoder = passEncoder;
    }

    public String getHealth() {
        return "Healthy";
    }

    public UserDTO save(UserDTO user, UserLevel level) throws Exception {
        User newUser = new User();

        if (user.password().isEmpty()) {
            throw new Exception("Password is required");
        }

        String pass = this.passEncoder.encode(user.password().get());
        newUser.setNewUser(user.name(), user.email(), pass, level);

        this.userRepository.save(newUser);

        return new UserDTO(Optional.of(newUser.getId()), newUser.getName(), newUser.getEmail(), null);
    }

    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> getUserAdmin() {
        return this.userRepository.findUserAdmin();
    }
}
