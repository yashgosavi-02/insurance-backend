package com.aastha.insurance.controller.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.insurance.LifeInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/insurance/life")
public class AdminLifeInsuranceController {

    private LifeInsuranceService lifeInsuranceService;

    public AdminLifeInsuranceController(LifeInsuranceService lifeInsuranceService) {
        this.lifeInsuranceService = lifeInsuranceService;
    }

    @GetMapping("/get")
    public List<LifeInsurance> getAll(){
        return lifeInsuranceService.getAll();
    }

    @GetMapping("/get/{id}")
    public LifeInsurance getOne(@PathVariable Integer id){
        return lifeInsuranceService.getOne(id);
    }

    @PostMapping("/add")
    public List<LifeInsurance> add(@RequestBody LifeInsurance lifeInsurance){
        lifeInsuranceService.add(lifeInsurance);
        return lifeInsuranceService.add(lifeInsurance);
    }
    @PutMapping("/update")
    public List<LifeInsurance> update(@RequestBody LifeInsurance lifeInsurance){
        return lifeInsuranceService.modify(lifeInsurance);
    }
    @DeleteMapping("/get/{id}")
    public List<LifeInsurance> delete(@PathVariable Integer id){
        return lifeInsuranceService.delete(id);
    }

}
