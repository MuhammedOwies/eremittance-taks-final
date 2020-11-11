package com.evision.finance.eremittance.authenticator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"accessToken", "tokenType", "expiresIn"})
public class TokenVO implements Serializable {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}