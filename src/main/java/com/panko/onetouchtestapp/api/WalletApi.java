package com.panko.onetouchtestapp.api;

import com.panko.onetouchtestapp.api.response.WalletApiResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "WalletApi")
public interface WalletApi {

    @RequestMapping(value = "/wallet",
            produces = {"application/json;charset=utf-8", "application/problem+json"},
            method = RequestMethod.GET)
    ResponseEntity<WalletApiResponse> placeBet(@RequestParam(value = "gameId") Integer gameId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "currency") String currency);
}
