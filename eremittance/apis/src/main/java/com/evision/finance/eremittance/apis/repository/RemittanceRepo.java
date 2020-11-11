package com.evision.finance.eremittance.apis.repository;

import com.evision.finance.eremittance.apis.entity.RemittanceEO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceRepo extends JpaRepository<RemittanceEO, Long> {

}
