package by.misevich.app.core.convertors.dto;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeCurrencyCardFilterDTO {
    private CardType typeCard;
    private CurrencyType currency;
}
