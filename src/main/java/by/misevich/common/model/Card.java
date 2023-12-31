package by.misevich.common.model;

import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "m_card")
public class Card extends AbstractEntity{
    private String number;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency = CurrencyType.BYN;

    @Enumerated(EnumType.STRING)
    private CardType typeCard = CardType.CLASSIC;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_client")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) &&
                currency == card.currency &&
                typeCard == card.typeCard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, currency, typeCard);
    }
}
