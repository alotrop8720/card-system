package by.misevich.app.core.convertors.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "cards", callSuper = false)
public class ClientDTO extends AbstractDTO{
    private String name;
    private String phone;
    private String email;
    private String status;
    @JsonIgnore
    private Set<CardDTO> cards;
}
