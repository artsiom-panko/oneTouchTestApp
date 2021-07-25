package com.panko.onetouchtestapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameUri(operatorId, gameId, currency));
    }
}
