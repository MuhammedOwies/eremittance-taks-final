package com.evision.finance.eremittance.authenticator.repository;

import com.evision.finance.eremittance.authenticator.entity.SysUserEO;
import com.evision.finance.eremittance.authenticator.entity.enums.StatusRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoImpl extends JpaRepository<SysUserEO, Long> {
    Boolean existsByUsernameOrEmail(String principal, String email);

    @Modifying
    @Query("UPDATE SysUserEO usr SET usr.displayName = ?1, usr.email = ?2, usr.status = ?3, usr.updatedBy = ?4, usr.updatedAt = current_timestamp WHERE usr.id = ?5")
    void updateUserEO(String displayName, String emailAddress, StatusRef status,
                      String securityPrincipal, Long id);

    @Modifying
    @Query("UPDATE SysUserEO usr SET usr.password = ?1, usr.updatedBy = ?2, usr.updatedAt = current_timestamp WHERE usr.id = ?3")
    void updateUserEOCredentials(String newCredentials, String securityPrincipal, Long id);

    @Modifying
    @Query("UPDATE SysUserEO usr SET usr.status = 'DELT', usr.updatedBy = ?1, usr.updatedAt = current_timestamp WHERE usr.id = ?2")
    void removeUserEO(String securityPrincipal, Long id);
}