package com.fpmislata.tienda_back.pay.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorizacionDto(
        String login,
        @JsonProperty("api_token") String apiToken
) {}
