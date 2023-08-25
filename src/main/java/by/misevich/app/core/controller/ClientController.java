package by.misevich.app.core.controller;

import by.misevich.app.core.dto.ClientDTO;
import by.misevich.app.core.filter.ClientFilter;
import by.misevich.app.core.services.ClientService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - Successfully created"),
            @ApiResponse(code = 406, message = "Not Acceptable - Username already use"),
            @ApiResponse(code = 400, message = "Bad Request - Can not convert Client. Invalid input data."),
            @ApiResponse(code = 403, message = "Forbidden - No access. Take a token."),
    })
    @PostMapping()
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO newClient){
        return clientService.createClient(newClient);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully retrieved"),
            @ApiResponse(code = 403, message = "Forbidden - No access. Take a token."),
    })
    @PostMapping("/search/by-contain")
    public ResponseEntity<List<ClientDTO>> findByContainClient(@RequestBody ClientFilter filter){
        return clientService.findByContainClient(filter);
    }

}
