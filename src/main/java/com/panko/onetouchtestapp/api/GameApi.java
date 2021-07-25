package com.panko.onetouchtestapp.api;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

@Api(value = "GameApi")
public interface GameApi {

    @RequestMapping(value = "/gameURI",
            produces = {"application/json;charset=utf-8", "application/problem+json"},
            method = RequestMethod.GET)
    ResponseEntity<URI> getGameUri(@RequestParam(value = "operatorId") Integer operatorId,
            @RequestParam(value = "gameId") Integer gameId,
            @RequestParam(value = "currency") String currency) throws HttpClientErrorException;
}