package com.evision.finance.eremittance.authenticator.entity;

import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import com.evision.finance.eremittance.authenticator.entity.validations.Password;
import com.evision.finance.eremittance.authenticator.entity.validations.Phone;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"id", "partner", "principal", "credentials", "firstName", "middleName", "lastName", "displayName", "jobTitle", "phoneNumber", "mobileNumber", "faxNumber", "emailAddress", "branch", "manager", "group", "status"})
public class UserVO implements Serializable {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String principal;

    @NotNull
    @NotBlank
    @Password
    @Size(max = 64)
    private String credentials;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String firstName;

    @Size(max = 64)
    private String middleName;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 192)
    private String displayName;

    @Size(max = 128)
    private String jobTitle;

    @Phone
    @Size(max = 35)
    private String phoneNumber;

    @Phone
    @Size(max = 35)
    private String mobileNumber;

    @Phone
    @Size(max = 35)
    private String faxNumber;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String emailAddress;

    @Size(max = 128)
    private String branch;

    private UserVO manager;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusRef status;
}
