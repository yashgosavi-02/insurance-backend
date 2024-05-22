package com.aastha.insurance.service.insurance;

import com.aastha.insurance.dao.insurance.HomeInsuranceRepository;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService{

    private HomeInsuranceRepository repository;

    public HomeInsuranceServiceImpl(HomeInsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HomeInsurance> getAll() {
        return repository.findAll();
    }

    @Override
    public HomeInsurance getOne(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<HomeInsurance> add(HomeInsurance homeInsurance) {
        repository.save(homeInsurance);
        return repository.findAll();
    }

    @Override
    public List<HomeInsurance> delete(Integer id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    @Override
    public List<HomeInsurance> modify(HomeInsurance homeInsurance) {
        repository.save(homeInsurance);
        return repository.findAll();
    }

    @Override
    public List<HomeInsurance> findByHouseValue(String houseValue) {
        return repository.findAllByHouseValue(houseValue);
    }

    @Override
    public List<HomeInsurance> findByHouseholdItemsValue(String householdItemsValue) {
        return repository.findAllByHouseholdItemsValue(householdItemsValue);
    }

    @Override
    public List<HomeInsurance> findByPolicyTerm(String policyTerm) {
        return repository.findAllByPolicyTerm(policyTerm);
    }

    @Override
    public List<HomeInsurance> findByHouseAge(String houseAge) {
        return repository.findAllByHouseAge(houseAge);
    }
}
