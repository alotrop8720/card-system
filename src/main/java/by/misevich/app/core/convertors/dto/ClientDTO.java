package by.misevich.app.core.convertors.dto;

import by.misevich.common.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO extends AbstractDTO{
    private String name;
    private String phone;
    private String email;
    private CardType status;
    private Set<CardDTO> cards;
}
