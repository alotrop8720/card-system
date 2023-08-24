package by.misevich.app.core.convertors.dto;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
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
    private CurrencyType currency;
    private CardType typeCard;
    private ClientDTO client;
}
