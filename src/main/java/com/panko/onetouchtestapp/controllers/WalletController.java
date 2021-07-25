package com.panko.onetouchtestapp.controllers;

import com.panko.onetouchtestapp.api.WalletApi;
import com.panko.onetouchtestapp.api.response.WalletApiResponse;
import com.panko.onetouchtestapp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController implements WalletApi {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public ResponseEntity<WalletApiResponse> placeBet(Integer gameId, Integer amount, String currency) {
        return null;
    }
}
