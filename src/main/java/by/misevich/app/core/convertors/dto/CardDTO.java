package by.misevich.app.core.convertors.dto;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "client", callSuper = false)
public class CardDTO extends AbstractDTO{
    private String number;
    private CurrencyType currency;
    private CardType typeCard;
    private ClientDTO client;
}
