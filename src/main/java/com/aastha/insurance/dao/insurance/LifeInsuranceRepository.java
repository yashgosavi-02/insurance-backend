package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifeInsuranceRepository extends JpaRepository<LifeInsurance,Integer> {
    List<LifeInsurance>
            findAllByPolicyTermAndCoverageAmountAndMedicalTestAndSmokerStatusAndGender(
                    String policyTerm,String coverageAmount,String medicalTest,String smokerStatus,String gender);
    List<LifeInsurance> findAllByGender(String gender);
    List<LifeInsurance> findAllByCoverageAmount(String coverageAmount);
    List<LifeInsurance> findAllByPolicyTerm(String policyTerm);
    List<LifeInsurance> findAllByMedicalTest(String medicalTest);
    List<LifeInsurance> findAllBySmokerStatus(String smokerStatus);
    List<LifeInsurance> findAllByAge(String age);
}
