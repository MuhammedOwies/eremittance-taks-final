package com.evision.finance.eremittance.apis.repository;

import com.evision.finance.eremittance.apis.entity.PartnerEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepoImpl extends JpaRepository<PartnerEO, Long> {
}
