package by.misevich.common.repository;

import by.misevich.common.model.Card;
import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findByCurrencyAndTypeCard(CurrencyType currency, CardType typeCard);
}
