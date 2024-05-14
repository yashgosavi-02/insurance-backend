package com.aastha.insurance.service.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;

import java.util.List;

public interface LifeInsuranceService {
    public List<LifeInsurance> getAll();
    public LifeInsurance getOne(Integer id);
    public List<LifeInsurance> add(LifeInsurance lifeInsurance);
    public List<LifeInsurance> delete(Integer id);
    public List<LifeInsurance> modify(LifeInsurance lifeInsurance);
}
