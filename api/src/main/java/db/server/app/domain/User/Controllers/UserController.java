package db.server.app.domain.User.Controllers;

import db.server.app.commons.Enums.UserLevel;
import db.server.app.commons.Records.Http.HttpErrorResponse;
import db.server.app.domain.User.DTO.UserDTO;
import db.server.app.domain.User.Model.User;
import db.server.app.domain.User.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/health")
    public String Health() {
        return this.userService.getHealth();
    }

    @PostMapping("/admin")
    public ResponseEntity NewAdminUser(
            @RequestBody UserDTO userDTO
    ) throws Exception {
        Optional<User> adminUser = this.userService.getUserAdmin();
        if (!adminUser.isEmpty()) {
            Optional<String> message = Optional.of("User admin already exists");
            return ResponseEntity.
                    status(HttpStatus.NOT_ACCEPTABLE).
                    body(new HttpErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), message));
        }

        UserDTO response = this.userService.save(userDTO, UserLevel.ADMIN);

        return ResponseEntity.ok(response);
    }
}
