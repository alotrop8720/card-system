package by.misevich.app.core.services;

import by.misevich.app.core.dto.CardDTO;
import by.misevich.app.core.dto.ClientDTO;
import by.misevich.app.core.dto.TypeCurrencyCardFilterDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;
    private final CardMapper cardMapper;

    @Transactional
    public ResponseEntity<CardDTO> createCard(CardDTO newCard){
        final ClientDTO clientDto = newCard.getClient();
        final Card card = cardMapper.toEntity(newCard);
        if (Objects.isNull(card)){
            log.info("Parser error: Card to CardDTO");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final Long clientId = clientDto.getId();

        final Optional<Client> clientById = clientRepository.findById(clientId);
        if (clientById.isEmpty()){
            log.error(String.format("Client with id = [%s] not found", clientId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        final Client client = clientById.get();
        client.setStatus(newCard.getTypeCard().name());

        clientRepository.save(client);
        final Card save = cardRepository.save(card);
        final CardDTO result = cardMapper.toDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    public ResponseEntity<Page<String>> findByTypeCurrencyCard(TypeCurrencyCardFilterDTO filter, Pageable pageable) {
        final CurrencyType currency = filter.getCurrency();
        final CardType type = filter.getTypeCard();
        final List<Card> result;
        if (Objects.isNull(currency) && Objects.nonNull(type)){
            result = cardRepository.findByTypeCard(type);
        } else if (Objects.isNull(type) && Objects.nonNull(currency)){
            result = cardRepository.findByCurrency(currency);
        } else if (Objects.nonNull(type)){
            result = cardRepository.findByCurrencyAndTypeCard(currency, type);
        } else {
            log.error("Parameters currency and type are empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final List<String> listPhones = result.stream().map(Card::getClient).map(Client::getPhone).collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(listPhones, pageable, listPhones.size()));
    }
}
