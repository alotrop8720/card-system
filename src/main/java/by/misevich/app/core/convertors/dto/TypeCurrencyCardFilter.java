package by.misevich.app.core.convertors.dto;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeCurrencyCardFilter {
    private CardType type;
    private CurrencyType currency;
}
