package com.aastha.insurance.dao.insurance;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance,Integer> {
    public List<HealthInsurance> findAllByCoverageAmountAndPolicyTermAndAndChronicDiseaseAndGenderAndAge(
             String coverageAmount, String policyTerm,
            String chronicDisease, String gender, String age);
    public List<HealthInsurance> findAllByGender(String gender);
    public List<HealthInsurance> findAllByCoverageAmount(String coverageAmount);
    public List<HealthInsurance> findAllByPolicyTerm(String policyTerm);
    public List<HealthInsurance> findAllByChronicDisease(String smokerStatus);
    public List<HealthInsurance> findAllByAge(String age);
}
