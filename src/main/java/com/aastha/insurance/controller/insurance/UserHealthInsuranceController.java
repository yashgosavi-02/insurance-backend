package com.aastha.insurance.controller.insurance;


import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.service.insurance.HealthInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/insurance/health")
@CrossOrigin(origins = "http://localhost:5173")
public class UserHealthInsuranceController {
    private HealthInsuranceService healthInsuranceService;

    public UserHealthInsuranceController(HealthInsuranceService healthInsuranceService) {
        this.healthInsuranceService = healthInsuranceService;
    }

    @GetMapping("/filter")
    public List<HealthInsurance> sort(@RequestParam(value = "policyTerm", defaultValue = "all") String term,
                                    @RequestParam(value = "coverageAmount", defaultValue = "all") String cover,
                                    @RequestParam(value = "chronic", defaultValue = "all") String chronic,
                                    @RequestParam(value = "gender", defaultValue = "all") String gender,
                                    @RequestParam(value = "age", defaultValue = "all") String age) {

        if (Objects.equals(term, "all") && Objects.equals(cover, "all") && Objects.equals(chronic, "all")
                && Objects.equals(gender, "all") && Objects.equals(age, "all")) {
            return healthInsuranceService.getAll();
        }

        List<HealthInsurance> result = healthInsuranceService.getAll();

        if (!Objects.equals(term, "all")) {
            List<HealthInsurance> terms = healthInsuranceService.findByPolicyTerm(term);
            result.retainAll(terms);
        }

        if (!Objects.equals(cover, "all")) {
            List<HealthInsurance> covers = healthInsuranceService.findByCoverageAmount(cover);
            result.retainAll(covers);
        }

        if (!Objects.equals(chronic, "all")) {
            List<HealthInsurance> smokers = healthInsuranceService.findByChronicDisease(chronic);
            result.retainAll(smokers);
        }

        if (!Objects.equals(gender, "all")) {
            List<HealthInsurance> genders = healthInsuranceService.findByGender(gender);
            result.retainAll(genders);
        }

        if (!Objects.equals(age, "all")) {
            List<HealthInsurance> ages = healthInsuranceService.findByAge(age);
            result.retainAll(ages);
        }

        return result;
    }
}
