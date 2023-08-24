package by.misevich.app.core.convertors.mappers;

import by.misevich.app.core.convertors.Mapper;
import by.misevich.app.core.convertors.dto.CardDTO;
import by.misevich.common.model.Card;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper implements Mapper<Card, CardDTO> {
    private final ModelMapper mapper;

    @Override
    public Card toEntity(CardDTO dto) {
        return mapper.map(dto, Card.class);
    }

    @Override
    public CardDTO toDto(Card entity) {
        return mapper.map(entity, CardDTO.class);
    }
}
