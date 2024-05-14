package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeInsuranceRepository extends JpaRepository<LifeInsurance,Integer> {
}
