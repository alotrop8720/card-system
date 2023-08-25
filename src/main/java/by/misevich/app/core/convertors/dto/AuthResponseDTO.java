package by.misevich.app.core.convertors.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class AuthResponseDTO {
    private String token;
    private String username;
}
