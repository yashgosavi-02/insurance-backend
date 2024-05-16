package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifeInsuranceRepository extends JpaRepository<LifeInsurance,Integer> {
    List<LifeInsurance> findAllByGender(String gender);
    List<LifeInsurance>
            findAllByPolicyTermAndCoverageAmountAndMedicalTestAndSmokerStatusAndGender(
                    int policyTerm,String coverageAmount,String medicalTest,String smokerStatus,String gender);
}
