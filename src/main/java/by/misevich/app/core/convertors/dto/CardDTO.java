package by.misevich.app.core.convertors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO extends AbstractDTO{
    private String number;
    private String currency;
    private String typeCard;
    private ClientDTO client;
}
