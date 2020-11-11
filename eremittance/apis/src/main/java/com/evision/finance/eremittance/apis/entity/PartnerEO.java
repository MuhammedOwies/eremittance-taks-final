package com.evision.finance.eremittance.apis.entity;

import com.evision.finance.eremittance.authenticator.entity.validations.Bic;
import com.evision.finance.eremittance.authenticator.entity.validations.Phone;
import com.evision.finance.eremittance.authenticator.entity.enums.CountryRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)

public class PartnerEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 256)

    private String pname;



    @Size(max = 20)
    
    private String lei;

    @Bic
    @Size(min = 8, max = 11)
    
    private String bic;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 128)
    
    private String emailAddress;

    @Phone
    @Size(max = 35)
    
    private String phoneNumber;

    @Phone
    @Size(max = 35)
    
    private String faxNumber;

    @Size(max = 128)
    
    private String addressLine;

    @NotNull
    @Enumerated(EnumType.STRING)
    
    private CountryRef country;
}