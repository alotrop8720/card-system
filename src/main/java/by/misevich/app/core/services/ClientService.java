package by.misevich.app.core.services;

import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.convertors.mappers.ClientMapper;
import by.misevich.common.model.Client;
import by.misevich.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ResponseEntity<ClientDTO> createClient(ClientDTO newClient){
        Client client = clientMapper.toEntity(newClient);
        if (client == null){
            log.info("Parser error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final Client save = clientRepository.save(client);
        final ClientDTO result = clientMapper.toDto(save);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}