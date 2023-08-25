package by.misevich.app.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "cards", callSuper = false)
public class ClientDTO extends AbstractDTO{
    private String fullName;
    private String phone;
    private String email;
    private String status;
    private String username;
    private String securePassword;
    @JsonIgnore
    private Set<CardDTO> cards;
}
