package by.misevich.app.core.controller;

import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @PostMapping()
    public ResponseEntity<ClientDTO> createEmployer(@RequestBody ClientDTO newClient){
        return clientService.createClient(newClient);
    }

}
