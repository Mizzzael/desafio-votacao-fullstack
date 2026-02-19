package db.server.app.commons.App.UseCases;

import db.server.app.commons.Helpers.JWT.JWTTokenGenerate;
import db.server.app.domain.User.Interfaces.IPassEncoder;
import db.server.app.domain.User.DTO.AuthResponse;
import db.server.app.domain.User.DTO.LoginCommand;
import db.server.app.domain.User.Helpers.Exceptions.InvalidCredentials;
import db.server.app.domain.User.Helpers.Exceptions.InvalidUser;
import db.server.app.domain.User.Model.User;
import db.server.app.domain.User.Repository.UserRepository;

import java.util.Optional;

public class AuthUserUseCase {

    private final IPassEncoder passEncoder;
    private final UserRepository userRepository;
    private final JWTTokenGenerate jwtTokenGenerate;

    public AuthUserUseCase(IPassEncoder passEncoder, UserRepository userRepository, JWTTokenGenerate jwtTokenGenerate) {
        this.passEncoder = passEncoder;
        this.userRepository = userRepository;
        this.jwtTokenGenerate = jwtTokenGenerate;
    }

    public AuthResponse execute(LoginCommand command) {
        Optional<User> user = userRepository.findByEmail(command.email());

        if (user.isEmpty()) {
            throw new InvalidUser(command.email());
        }

        if (!passEncoder.matches(command.password(), user.get().getPassword())) {
            throw new InvalidCredentials();
        }

        String token = jwtTokenGenerate.generate(user.get());

        return new AuthResponse(token);
    }

}
