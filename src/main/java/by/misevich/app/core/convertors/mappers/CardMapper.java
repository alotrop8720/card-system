package by.misevich.app.core.convertors.mappers;

import by.misevich.app.core.convertors.AbstractMapper;
import by.misevich.app.core.dto.CardDTO;
import by.misevich.common.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardMapper extends AbstractMapper<Card, CardDTO> {

    @Autowired
    public CardMapper() {
        super(Card.class, CardDTO.class);
    }
}
