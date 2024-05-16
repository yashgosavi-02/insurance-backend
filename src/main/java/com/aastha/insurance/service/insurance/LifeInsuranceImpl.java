package com.aastha.insurance.service.insurance;

import com.aastha.insurance.dao.insurance.LifeInsuranceRepository;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifeInsuranceImpl implements LifeInsuranceService{


    private LifeInsuranceRepository repository;

    public LifeInsuranceImpl(LifeInsuranceRepository repository) {
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
    public List<LifeInsurance> findByFilter(int policyTerm, String coverageAmount, String medicalTestRequired, String smokerStatus, String gender) {
        return repository.findAllByPolicyTermAndCoverageAmountAndMedicalTestAndSmokerStatusAndGender(
        policyTerm,coverageAmount,medicalTestRequired,smokerStatus,gender
        );
    }
}
