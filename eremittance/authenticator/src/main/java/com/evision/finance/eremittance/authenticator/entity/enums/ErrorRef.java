package com.evision.finance.eremittance.authenticator.entity.enums;

public enum ErrorRef {
    GENERAL_ERROR(500),
    NO_DATA_FOUND(5000),
    NOT_REFERRED_CALL(5001),

    GROUP_ALREADY_EXIST(5101),
    GROUP_NOT_FOUND(5102),

    USER_ALREADY_EXIST(5201),
    USER_NOT_FOUND(5202),
    USER_INCORRECT_OLD_PASSWORD(5203),
    CREDENTIALS_RECENTLY_USED(5204),

    PARTNER_ALREADY_EXIST(5301),
    PARTNER_NOT_FOUND(5302),

    ROLE_ALREADY_EXIST(5401),
    ROLE_NOT_FOUND(5402),

    URL_ALREADY_EXIST(5501),
    URL_NOT_FOUND(5502),

    SETTING_ALREADY_EXIST(5601),
    SETTING_NOT_FOUND(5602),

    CHANNEL_NOT_FOUND(5702);

    private final int value;

    ErrorRef(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
