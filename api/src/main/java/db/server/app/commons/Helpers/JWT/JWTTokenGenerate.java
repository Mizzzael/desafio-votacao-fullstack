package db.server.app.commons.Helpers.JWT;

import db.server.app.commons.Interfaces.ITokenGenerate;
import db.server.app.domain.User.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenGenerate implements ITokenGenerate {

    @Override
    public String generate(User user) {
        SignKey sk = new SignKey();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(sk.getSignKey(), SignatureAlgorithm.ES256)
                .compact();
    }
}
