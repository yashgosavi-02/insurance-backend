package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
HomeInsuranceRepository extends JpaRepository<HomeInsurance,Integer> {

    List<HomeInsurance> findAllByHouseValue(String houseValue);
    List<HomeInsurance> findAllByHouseholdItemsValue(String householdItemsValue);
    List<HomeInsurance> findAllByPolicyTerm(String policyTerm);
    List<HomeInsurance> findAllByHouseAge(String houseAge);
    List<HomeInsurance> findAllByCompany(String company);
}
