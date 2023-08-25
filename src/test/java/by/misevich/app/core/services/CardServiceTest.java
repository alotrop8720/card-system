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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @InjectMocks
    private CardService cardService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CardMapper cardMapper;

    private final static Long TEST_ID = 1L;
    private final static Integer TIME_TO_CALL = 1;
    private final static PageRequest PAGEABLE = PageRequest.of(0, 10);

    @Test
    void createCard() {
        ClientDTO clientDto = new ClientDTO();
        clientDto.setId(TEST_ID);

        CardDTO cardDto = new CardDTO();
        cardDto.setClient(clientDto);
        cardDto.setTypeCard(CardType.GOLD);

        Client client = new Client();
        Card card = new Card();

        when(cardMapper.toEntity(cardDto)).thenReturn(card);
        when(clientRepository.findById(TEST_ID)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        when(cardRepository.save(card)).thenReturn(card);
        when(cardMapper.toDto(card)).thenReturn(cardDto);
        cardService.createCard(cardDto);

        assertThat(client.getStatus()).isEqualTo(CardType.GOLD.name());
        verify(cardRepository, times(TIME_TO_CALL)).save(card);
    }

    @Test
    void findByTypeCurrencyCard_checkAllParameters() {
        TypeCurrencyCardFilterDTO filter = new TypeCurrencyCardFilterDTO();
        filter.setTypeCard(CardType.GOLD);
        filter.setCurrency(CurrencyType.USD);

        final CurrencyType currency = filter.getCurrency();
        final CardType typeCard = filter.getTypeCard();

        when(cardRepository.findByCurrencyAndTypeCard(currency, typeCard)).thenReturn(Collections.emptyList());

        cardService.findByTypeCurrencyCard(filter, PAGEABLE);

        verify(cardRepository, times(TIME_TO_CALL)).findByCurrencyAndTypeCard(currency, typeCard);
    }

    @Test
    void findByTypeCurrencyCard_checkOnlyTypeParameters() {
        TypeCurrencyCardFilterDTO filter = new TypeCurrencyCardFilterDTO();
        filter.setTypeCard(CardType.GOLD);

        final CardType typeCard = filter.getTypeCard();

        when(cardRepository.findByTypeCard(typeCard)).thenReturn(Collections.emptyList());

        cardService.findByTypeCurrencyCard(filter, PAGEABLE);

        verify(cardRepository, times(TIME_TO_CALL)).findByTypeCard(typeCard);
    }

    @Test
    void findByTypeCurrencyCard_checkOnlyCurrencyParameters() {
        TypeCurrencyCardFilterDTO filter = new TypeCurrencyCardFilterDTO();
        filter.setCurrency(CurrencyType.USD);

        final CurrencyType currency = filter.getCurrency();

        when(cardRepository.findByCurrency(currency)).thenReturn(Collections.emptyList());

        cardService.findByTypeCurrencyCard(filter, PAGEABLE);

        verify(cardRepository, times(TIME_TO_CALL)).findByCurrency(currency);
    }
}