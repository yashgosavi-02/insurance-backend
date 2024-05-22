package com.aastha.insurance.controller.insurance.admin;

import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import com.aastha.insurance.service.insurance.HomeInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance/home")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminHomeInsuranceController {

    private HomeInsuranceService homeInsuranceService;

    public AdminHomeInsuranceController(HomeInsuranceService homeInsuranceService) {
        this.homeInsuranceService = homeInsuranceService;
    }

    @GetMapping("/get")
    public List<HomeInsurance> getAll()
    {
        return homeInsuranceService.getAll();
    }

    @GetMapping("/get/{id}")
    public HomeInsurance getOne(@PathVariable Integer id){
        return homeInsuranceService.getOne(id);
    }

    @PostMapping("/add")
    public List<HomeInsurance> add(@RequestBody HomeInsurance homeInsurance){
        return homeInsuranceService.add(homeInsurance);
    }
    @PutMapping("/update")
    public List<HomeInsurance> update(@RequestBody HomeInsurance homeInsurance){
        return homeInsuranceService.modify(homeInsurance);
    }
    @DeleteMapping("/get/{id}")
    public List<HomeInsurance> delete(@PathVariable Integer id){
        return homeInsuranceService.delete(id);
    }

}
