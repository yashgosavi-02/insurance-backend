package com.aastha.insurance.service.insurance;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;

import java.util.List;

public interface LifeInsuranceService {
    public List<LifeInsurance> getAll();
    public LifeInsurance getOne(Integer id);
    public List<LifeInsurance> add(LifeInsurance lifeInsurance);
    public List<LifeInsurance> delete(Integer id);
    public List<LifeInsurance> modify(LifeInsurance lifeInsurance);
    public List<LifeInsurance> findByGender(String gender);
    public List<LifeInsurance> findByCoverageAmount(String coverageAmount);
    public List<LifeInsurance> findByPolicyTerm(String policyTerm);
    public List<LifeInsurance> findByMedicalTest(String medicalTest);
    public List<LifeInsurance> findBySmokerStatus(String smokerStatus);
    public List<LifeInsurance> findByAge(String age);
    public List<LifeInsurance> findByCompany(String company);
    public List<LifeInsurance> findByFilter(
            String policyTerm,String coverageAmount,String medicalTestRequired,String smokerStatus,String gender);
}
