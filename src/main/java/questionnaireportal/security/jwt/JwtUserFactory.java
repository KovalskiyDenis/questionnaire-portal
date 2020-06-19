package questionnaireportal.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import questionnaireportal.models.User;

import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                null
        );
    }
}
