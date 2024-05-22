package com.aastha.insurance.controller.insurance.admin;


import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.service.insurance.HealthInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance/health")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminHealthInsuranceController {

    private HealthInsuranceService healthInsuranceService;

    public AdminHealthInsuranceController(HealthInsuranceService healthInsuranceService) {
        this.healthInsuranceService = healthInsuranceService;
    }

    @GetMapping("/get")
    public List<HealthInsurance> getAll()
    {
        return healthInsuranceService.getAll();
    }

    @GetMapping("/get/{id}")
    public HealthInsurance getOne(@PathVariable Integer id){
        return healthInsuranceService.getOne(id);
    }

    @PostMapping("/add")
    public List<HealthInsurance> add(@RequestBody HealthInsurance healthInsurance){
        return healthInsuranceService.add(healthInsurance);
    }
    @PutMapping("/update")
    public List<HealthInsurance> update(@RequestBody HealthInsurance healthInsurance){
        return healthInsuranceService.modify(healthInsurance);
    }
    @DeleteMapping("/get/{id}")
    public List<HealthInsurance> delete(@PathVariable Integer id){
        return healthInsuranceService.delete(id);
    }
}
