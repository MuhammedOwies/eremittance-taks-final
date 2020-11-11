package com.evision.finance.eremittance.authenticator.entity;

import com.evision.finance.eremittance.authenticator.entity.audit.Updatable;
import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import com.evision.finance.eremittance.authenticator.entity.validations.Phone;
import com.evision.finance.eremittance.authenticator.util.BCryptoAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

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
@Where(clause = "STATUS <> 'DELT'")
@Table(name = "USERS")
public class SysUserEO extends Updatable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Column(name = "USERNAME", length = 64, nullable = false, unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Convert(converter = BCryptoAttributeConverter.class)
    @Column(name = "PASSWORD", length = 64, nullable = false)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Column(name = "FISRT_NAME", length = 64, nullable = false)
    private String firstName;

    @Size(max = 64)
    @Column(name = "MIDDLE_NAME", length = 64)
    private String middleName;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Column(name = "LAST_NAME", length = 64, nullable = false)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 192)
    @Column(name = "DISPLAY_NAME", length = 192, nullable = false)
    private String displayName;

    @Size(max = 128)
    @Column(name = "JOB_TITLE", length = 128)
    private String jobTitle;

    @Phone
    @Size(max = 35)
    @Column(name = "PHONE", length = 35)
    private String phone;

    @Phone
    @Size(max = 35)
    @Column(name = "MOBILE", length = 35)
    private String mobile;

    @Phone
    @Size(max = 35)
    @Column(name = "FAX", length = 35)
    private String fax;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 128)
    @Column(name = "EMAIL", length = 128, nullable = false, unique = true)
    private String email;

    @Size(max = 128)
    @Column(name = "BRANCH", length = 128)
    private String branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private SysUserEO MANAGER;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS", length = 4, nullable = false)
    private StatusRef status;
}