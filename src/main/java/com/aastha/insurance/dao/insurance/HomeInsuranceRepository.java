package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.HomeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance,Integer> {
}
