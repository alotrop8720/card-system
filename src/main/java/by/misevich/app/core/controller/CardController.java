package by.misevich.app.core.controller;

import by.misevich.app.core.dto.CardDTO;
import by.misevich.app.core.dto.TypeCurrencyCardFilterDTO;
import by.misevich.app.core.services.CardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - Successfully created"),
            @ApiResponse(code = 404, message = "Not found - Client was not found"),
            @ApiResponse(code = 403, message = "Forbidden - No access. Take a token."),
            @ApiResponse(code = 400, message = "Bad Request - Can not convert Card. Invalid input data.")
    })
    @PostMapping()
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO newCard){
        return cardService.createCard(newCard);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully retrieved"),
            @ApiResponse(code = 403, message = "Forbidden - No access. Take a token."),
            @ApiResponse(code = 400, message = "Bad Request - Invalid input data.")
    })
    @PostMapping("/search/by-type-currency")
    public ResponseEntity<Page<String>> findByTypeCurrencyCard(@RequestBody TypeCurrencyCardFilterDTO filter, Pageable pageable){
        return cardService.findByTypeCurrencyCard(filter, pageable);
    }
}
