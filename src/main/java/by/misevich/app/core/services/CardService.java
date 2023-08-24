package by.misevich.app.core.services;

import by.misevich.app.core.convertors.dto.CardDTO;
import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.convertors.mappers.CardMapper;
import by.misevich.common.model.Card;
import by.misevich.common.model.Client;
import by.misevich.common.repository.CardRepository;
import by.misevich.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        client.setStatus(card.getTypeCard());

        clientRepository.save(client);
        final Card save = cardRepository.save(card);
        final CardDTO result = cardMapper.toDto(save);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }
}
