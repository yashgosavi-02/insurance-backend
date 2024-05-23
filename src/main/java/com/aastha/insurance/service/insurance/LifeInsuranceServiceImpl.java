package com.aastha.insurance.service.insurance;

import com.aastha.insurance.dao.insurance.LifeInsuranceRepository;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifeInsuranceServiceImpl implements LifeInsuranceService{


    private LifeInsuranceRepository repository;

    public LifeInsuranceServiceImpl(LifeInsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LifeInsurance> getAll() {
        return repository.findAll();
    }

    @Override
    public LifeInsurance getOne(Integer id) {
        Optional<LifeInsurance> result = repository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Life insurance with id "+id+" not exist");
        }
        return result.get();
    }

    @Override
    public List<LifeInsurance> add(LifeInsurance lifeInsurance) {
        repository.save(lifeInsurance);
        return repository.findAll();
    }

    @Override
    public List<LifeInsurance> delete(Integer id) {
        Optional<LifeInsurance> result = repository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Life insurance with id "+id+" not exist");
        }
        repository.deleteById(result.get().getId());
        return repository.findAll();
    }

    @Override
    public List<LifeInsurance> modify(LifeInsurance lifeInsurance) {
        repository.save(lifeInsurance);
        return repository.findAll();
    }

    @Override
    public List<LifeInsurance> findByGender(String gender) {
        return repository.findAllByGender(gender);
    }

    @Override
    public List<LifeInsurance> findByCoverageAmount(String coverageAmount) {
        return repository.findAllByCoverageAmount(coverageAmount);
    }

    @Override
    public List<LifeInsurance> findByPolicyTerm(String policyTerm) {
        return repository.findAllByPolicyTerm(policyTerm);
    }

    @Override
    public List<LifeInsurance> findByMedicalTest(String medicalTest) {
        return repository.findAllByMedicalTest(medicalTest);
    }

    @Override
    public List<LifeInsurance> findBySmokerStatus(String smokerStatus) {
        return repository.findAllBySmokerStatus(smokerStatus);
    }

    @Override
    public List<LifeInsurance> findByAge(String age) {
        return repository.findAllByAge(age);
    }

    @Override
    public List<LifeInsurance> findByCompany(String company) {
        return repository.findAllByCompany(company);
    }

    @Override
    public List<LifeInsurance> findByFilter(String policyTerm, String coverageAmount, String medicalTestRequired, String smokerStatus, String gender) {
        return repository.findAllByPolicyTermAndCoverageAmountAndMedicalTestAndSmokerStatusAndGender(
        policyTerm,coverageAmount,medicalTestRequired,smokerStatus,gender
        );
    }
}
