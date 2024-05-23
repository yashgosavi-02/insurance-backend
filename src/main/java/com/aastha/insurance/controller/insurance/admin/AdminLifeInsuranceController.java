package com.aastha.insurance.controller.insurance.admin;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.insurance.LifeInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/insurance/life")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminLifeInsuranceController {

    private LifeInsuranceService lifeInsuranceService;

    public AdminLifeInsuranceController(LifeInsuranceService lifeInsuranceService) {
        this.lifeInsuranceService = lifeInsuranceService;
    }

    @GetMapping("/get")
    public List<LifeInsurance> getAll()
    {
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
    @DeleteMapping("/delete/{id}")
    public List<LifeInsurance> delete(@PathVariable Integer id){
        return lifeInsuranceService.delete(id);
    }
    @GetMapping("/filter")
    public List<LifeInsurance> filter(@RequestParam(value = "company",defaultValue = "all") String company){
        if(Objects.equals(company,"all")){
            return lifeInsuranceService.getAll();
        }
        return lifeInsuranceService.findByCompany(company);
    }
}
