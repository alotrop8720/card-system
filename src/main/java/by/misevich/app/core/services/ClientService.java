package by.misevich.app.core.services;

import by.misevich.app.core.dto.ClientDTO;
import by.misevich.app.core.convertors.mappers.ClientMapper;
import by.misevich.app.core.error.InvalidLoginException;
import by.misevich.app.core.filter.ClientFilter;
import by.misevich.common.model.Client;
import by.misevich.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientMapper clientMapper;

    @Transactional
    public ResponseEntity<ClientDTO> createClient(ClientDTO newClient) {
        Client client = clientMapper.toEntity(newClient);

        if (Objects.isNull(client)) {
            log.info("Parser error: Client to ClientDTO");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        checkLogin(client.getUsername());
        client.setSecurePassword(passwordEncoder.encode(client.getSecurePassword()));

        final Client save = clientRepository.save(client);
        final ClientDTO result = clientMapper.toDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    private void checkLogin(String username){
        Client client = clientRepository.findByUsername(username);

        if (Objects.nonNull(client)){
            log.info("Login is used");
            throw new InvalidLoginException(username);
        }
    }

    public ResponseEntity<List<ClientDTO>> findByContainClient(ClientFilter filter) {
        final List<Client> collect = clientRepository.findAll(filter.toSpecification());

        final List<ClientDTO> result = collect.stream().map(clientMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
