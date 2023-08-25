package by.misevich.app.core.controller;

import by.misevich.app.core.convertors.dto.CardDTO;
import by.misevich.app.core.convertors.dto.TypeCurrencyCardFilterDTO;
import by.misevich.app.core.services.CardService;
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

    @PostMapping()
    public ResponseEntity<CardDTO> createEmployer(@RequestBody CardDTO newCard){
        return cardService.createCard(newCard);
    }

    @PostMapping("/search/by-type-currency")
    public ResponseEntity<Page<String>> findByTypeCurrencyCard(@RequestBody TypeCurrencyCardFilterDTO filter, Pageable pageable){
        return cardService.findByTypeCurrencyCard(filter, pageable);
    }
}
