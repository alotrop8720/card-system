package by.misevich.common.model;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    @Id
    private String id;
    private String number;
    private CurrencyType currency;
    private CardType type;
}
