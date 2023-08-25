package by.misevich.app.core.error;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;


@Getter
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
