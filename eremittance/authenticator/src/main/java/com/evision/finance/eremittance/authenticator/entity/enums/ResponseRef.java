package com.evision.finance.eremittance.authenticator.entity.enums;

public enum ResponseRef {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    SERVER_ERROR(500);

    private final int value;

    ResponseRef(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
