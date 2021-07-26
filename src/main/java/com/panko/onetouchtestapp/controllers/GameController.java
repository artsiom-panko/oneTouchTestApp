package com.panko.onetouchtestapp.controllers;

import com.panko.onetouchtestapp.api.GameApi;
import com.panko.onetouchtestapp.services.GameService;
import com.panko.onetouchtestapp.services.GameValidationService;
import com.panko.onetouchtestapp.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;

@RestController
public class GameController implements GameApi {
    private final GameService gameService;
    private final GameValidationService gameValidationService;

    @Autowired
    public GameController(GameService gameService, GameValidationService gameValidationService) {
        this.gameService = gameService;
        this.gameValidationService = gameValidationService;
    }

    @Override
    public ResponseEntity getGameUri(Integer operatorId, Integer gameId, String currency) {
        final Collection<ValidationError> errors = gameValidationService.validateGameApiRequest(operatorId, gameId, currency);
        final URI gameUri = gameService.getGameUri(operatorId, gameId, currency);

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        return ResponseEntity.status(HttpStatus.OK).body(gameUri);
    }
}
