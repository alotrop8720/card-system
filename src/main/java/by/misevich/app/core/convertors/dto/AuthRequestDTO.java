package by.misevich.app.core.convertors.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class AuthRequestDTO {
    private String username;
    private String securePassword;
}
