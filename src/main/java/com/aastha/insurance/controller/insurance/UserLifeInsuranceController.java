package com.aastha.insurance.controller.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.insurance.LifeInsuranceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/insurance/life")
public class UserLifeInsuranceController {

    private LifeInsuranceService lifeInsuranceService;

    public UserLifeInsuranceController(LifeInsuranceService lifeInsuranceService) {
        this.lifeInsuranceService = lifeInsuranceService;
    }

    @GetMapping("/filter")
    public List<LifeInsurance> sort(@RequestParam(value = "policyTerm",defaultValue = "all") int term,
                                    @RequestParam(value = "coverageAmount",defaultValue = "all") String cover,
                                    @RequestParam(value = "medicalTestRequired",defaultValue = "all") String medicalTest,
                                    @RequestParam(value = "smokerStatus",defaultValue = "all") String smokerStatus,
                                    @RequestParam(value = "gender",defaultValue = "male") String gender){
        List<LifeInsurance> result=null;
        if(Objects.equals(term, "all") && Objects.equals(cover, "all") && Objects.equals(medicalTest, "all")
                && Objects.equals(smokerStatus, "all") && Objects.equals(gender.toLowerCase(), "male")){
            result = lifeInsuranceService.findByGender("Male");
        }
        else {
           result= lifeInsuranceService.findByFilter(term,cover,medicalTest,smokerStatus,gender);
        }
        return result;
    }
}
