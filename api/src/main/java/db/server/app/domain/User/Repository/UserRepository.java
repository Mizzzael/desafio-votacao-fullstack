package db.server.app.domain.User.Repository;

import db.server.app.domain.User.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query("{ 'level' : 'ADMIN', }")
    Optional<User> findUserAdmin();
}
