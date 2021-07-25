package com.panko.onetouchtestapp.api.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalletApiResponse {
    private final String user;
    private final String status;
    private final String requestUuid;
    private final String currency;
    private final Integer balance;
}

