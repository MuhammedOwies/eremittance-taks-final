package com.evision.finance.eremittance.apis.view;

import com.evision.finance.eremittance.authenticator.entity.validations.Bic;
import com.evision.finance.eremittance.authenticator.entity.validations.Phone;
import com.evision.finance.eremittance.authenticator.entity.enums.CountryRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder(value = {"id", "name", "lei", "bic", "emailAddress", "phoneNumber", "faxNumber", "addressLine", "country"})
public class PartnerVO implements Serializable {

    private Long id;

    private String name;



    private String lei;

    @Bic
    private String bic;

    @Email
    private String emailAddress;

    @Phone
    private String phoneNumber;

    @Phone
    private String faxNumber;

    private String addressLine;

    private CountryRef country;
}
