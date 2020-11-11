package com.evision.finance.eremittance.authenticator.entity.enums;

public enum StatusRef {
    INAC(0),
    ACTV(1),
    DELT(2);

    private int value;

    StatusRef(int value) {
        this.value = value;
    }
}
