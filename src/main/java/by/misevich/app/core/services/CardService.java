package by.misevich.app.core.services;

import by.misevich.app.core.convertors.dto.CardDTO;
import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.convertors.dto.TypeCurrencyCardFilter;
import by.misevich.app.core.convertors.mappers.CardMapper;
import by.misevich.common.model.Card;
import by.misevich.common.model.Client;
import by.misevich.common.model.enums.CardType;
import by.misevich.common.model.enums.CurrencyType;
import by.misevich.common.repository.CardRepository;
import by.misevich.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;
    private final CardMapper cardMapper;

    public ResponseEntity<CardDTO> createCard(CardDTO newCard){
        final ClientDTO clientDto = newCard.getClient();
        final Card card = cardMapper.toEntity(newCard);

        final Optional<Client> clientById = clientRepository.findById(clientDto.getId());
        if (clientById.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final Client client = clientById.get();
        client.setStatus(card.getTypeCard().name());

        clientRepository.save(client);
        final Card save = cardRepository.save(card);
        final CardDTO result = cardMapper.toDto(save);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    public ResponseEntity<Page<String>> typeCurrencyCardFilter(TypeCurrencyCardFilter filter, Pageable pageable) {
        final CurrencyType currency = filter.getCurrency();
        final CardType type = filter.getType();
        final List<Card> result = cardRepository.findByCurrencyAndTypeCard(currency,type);
        final List<String> listPhones = result.stream().map(Card::getClient).map(Client::getPhone).collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(listPhones, pageable, listPhones.size()));
    }
}
