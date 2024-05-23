package com.aastha.insurance.service.insurance;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;

import java.util.List;

public interface HealthInsuranceService {
    public List<HealthInsurance> getAll();
    public HealthInsurance getOne(Integer id);
    public List<HealthInsurance> add(HealthInsurance healthInsurance);
    public List<HealthInsurance> delete(Integer id);
    public List<HealthInsurance> modify(HealthInsurance healthInsurance);
    public List<HealthInsurance> findByGender(String gender);
    public List<HealthInsurance> findByCoverageAmount(String coverageAmount);
    public List<HealthInsurance> findByPolicyTerm(String policyTerm);
    public List<HealthInsurance> findByChronicDisease(String disease);
    public List<HealthInsurance> findByAge(String age);
    public List<HealthInsurance> findByCompany(String company);
}
