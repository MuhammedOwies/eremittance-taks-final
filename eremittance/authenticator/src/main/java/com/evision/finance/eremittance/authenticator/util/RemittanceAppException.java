package com.evision.finance.eremittance.authenticator.util;

import com.evision.finance.eremittance.authenticator.entity.enums.ErrorRef;
import com.evision.finance.eremittance.authenticator.entity.enums.ResponseRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RemittanceAppException extends RuntimeException {
    private ErrorRef errorCode;
    private ResponseRef responseCode;
    private Date timestamp;

    public RemittanceAppException(String message) {
        super(message);
        this.timestamp = new Date();
    }

    public RemittanceAppException(Throwable cause) {
        super(cause);
        this.timestamp = new Date();
    }

    public RemittanceAppException(ErrorRef errorCode, ResponseRef responseCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.responseCode = responseCode;

    }

    public RemittanceAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemittanceAppException(ErrorRef errorCode, ResponseRef responseCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.responseCode = responseCode;

    }


    public RemittanceAppException(String message, String description) {
        super(message+description);
    }
}
