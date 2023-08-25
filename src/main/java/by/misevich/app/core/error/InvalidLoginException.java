package by.misevich.app.core.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;

public class InvalidLoginException extends ResponseStatusException {

    public InvalidLoginException(String username) {
        super(HttpStatus.NOT_ACCEPTABLE, format("Username is already use; username = '%s'", username));
    }
}
