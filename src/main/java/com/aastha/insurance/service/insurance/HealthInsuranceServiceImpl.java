package com.aastha.insurance.service.insurance;

import com.aastha.insurance.dao.insurance.HealthInsuranceRepository;
import com.aastha.insurance.entity.insurance.HealthInsurance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService{

    private HealthInsuranceRepository repository;

    public HealthInsuranceServiceImpl(HealthInsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HealthInsurance> getAll() {
        return repository.findAll();
    }

    @Override
    public HealthInsurance getOne(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<HealthInsurance> add(HealthInsurance healthInsurance) {
        repository.save(healthInsurance);
        return repository.findAll();
    }

    @Override
    public List<HealthInsurance> delete(Integer id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    @Override
    public List<HealthInsurance> modify(HealthInsurance healthInsurance) {
        repository.save(healthInsurance);
        return repository.findAll();
    }

    @Override
    public List<HealthInsurance> findByGender(String gender) {
        return repository.findAllByGender(gender);
    }

    @Override
    public List<HealthInsurance> findByCoverageAmount(String coverageAmount) {
        return repository.findAllByCoverageAmount(coverageAmount);
    }

    @Override
    public List<HealthInsurance> findByPolicyTerm(String policyTerm) {
        return repository.findAllByPolicyTerm(policyTerm);
    }

    @Override
    public List<HealthInsurance> findByChronicDisease(String disease) {
        return repository.findAllByChronicDisease(disease);
    }

    @Override
    public List<HealthInsurance> findByAge(String age) {
        return repository.findAllByAge(age);
    }
}
