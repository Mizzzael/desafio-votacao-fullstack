package db.server.app.domain.User.Model;

import db.server.app.commons.Enums.UserLevel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private UserLevel level;

    public void setNewUser(String name, String email, String password, UserLevel level) {
        this.id = new ObjectId().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level.toString();
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
