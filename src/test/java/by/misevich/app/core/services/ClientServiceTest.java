package by.misevich.app.core.services;

import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.convertors.mappers.ClientMapper;
import by.misevich.app.core.filter.ClientFilter;
import by.misevich.common.model.Client;
import by.misevich.common.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;

import static by.misevich.app.core.filter.specification.ClientSpecification.hasEmail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    private final static Integer TIME_TO_CALL = 1;

    @Test
    void createClient() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();

        when(clientMapper.toEntity(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDTO);

        clientService.createClient(clientDTO);

        verify(clientRepository, times(TIME_TO_CALL)).save(client);
    }


    @Captor
    ArgumentCaptor<Specification<Client>> specificationArgumentCaptor;

    @Test
    void findByContainClient() {
        ClientFilter clientFilter = new ClientFilter();

        when(clientRepository.findAll(specificationArgumentCaptor.capture())).thenReturn(Collections.emptyList());

        clientService.findByContainClient(clientFilter);

        verify(clientRepository, times(TIME_TO_CALL)).findAll(specificationArgumentCaptor.capture());
    }
}