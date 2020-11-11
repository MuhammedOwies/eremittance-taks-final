package com.evision.finance.eremittance.apis.view;

import com.evision.finance.eremittance.authenticator.entity.validations.Phone;
import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RemittanceVO {
    Long id;
    @NotNull
    @NotBlank
    @Size(max = 128)
    String senderName;
    @NotBlank
    @NotNull
    String senderId;
    @NotBlank
    @NotNull
    String typeOfId;
    @Phone
    String senderPhone;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String emailAddress;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusRef status;
    @Basic(optional = false)
    private PartnerVO partner;

    @NotBlank
    @NotNull
    private String remittanceAmount;

    @NotBlank
    @NotNull
    private String countryArName;
    @NotNull
    private Integer countryId;
    @NotNull
    private Integer companyId;
    @NotNull
    private Short currencyCode;
    @NotNull
    private Short companyCommission;
    @NotNull
    private Short benficiaryCommission;
}
