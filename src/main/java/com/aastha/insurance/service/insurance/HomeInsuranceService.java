package com.aastha.insurance.service.insurance;

import com.aastha.insurance.entity.insurance.HomeInsurance;

import java.util.List;

public interface HomeInsuranceService {

    public List<HomeInsurance> getAll();
    public HomeInsurance getOne(Integer id);
    public List<HomeInsurance> add(HomeInsurance homeInsurance);
    public List<HomeInsurance> delete(Integer id);
    public List<HomeInsurance> modify(HomeInsurance homeInsurance);
    public List<HomeInsurance> findByHouseValue(String houseValue);
    public List<HomeInsurance> findByHouseholdItemsValue(String householdItemsValue);
    public List<HomeInsurance> findByPolicyTerm(String policyTerm);
    public List<HomeInsurance> findByHouseAge(String houseAge);
}
