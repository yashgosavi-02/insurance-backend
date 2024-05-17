package com.aastha.insurance.controller.insurance;

import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.insurance.LifeInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/insurance/life")
@CrossOrigin(origins = "http://localhost:5173")
public class UserLifeInsuranceController {

    private LifeInsuranceService lifeInsuranceService;

    public UserLifeInsuranceController(LifeInsuranceService lifeInsuranceService) {
        this.lifeInsuranceService = lifeInsuranceService;
    }

    @GetMapping("/filter")
    public List<LifeInsurance> sort(@RequestParam(value = "policyTerm", defaultValue = "all") String term,
                                    @RequestParam(value = "coverageAmount", defaultValue = "all") String cover,
                                    @RequestParam(value = "medicalTestRequired", defaultValue = "all") String medicalTest,
                                    @RequestParam(value = "smokerStatus", defaultValue = "all") String smokerStatus,
                                    @RequestParam(value = "gender", defaultValue = "all") String gender,
                                    @RequestParam(value = "age", defaultValue = "all") String age) {

        if (Objects.equals(term, "all") && Objects.equals(cover, "all") && Objects.equals(medicalTest, "all")
                && Objects.equals(smokerStatus, "all") && Objects.equals(gender, "all") && Objects.equals(age, "all")) {
            return lifeInsuranceService.getAll();
        }

        List<LifeInsurance> result = lifeInsuranceService.getAll();

        if (!Objects.equals(term, "all")) {
            List<LifeInsurance> terms = lifeInsuranceService.findByPolicyTerm(term);
            result.retainAll(terms);
        }

        if (!Objects.equals(cover, "all")) {
            List<LifeInsurance> covers = lifeInsuranceService.findByCoverageAmount(cover);
            result.retainAll(covers);
        }

        if (!Objects.equals(medicalTest, "all")) {
            List<LifeInsurance> medicalTests = lifeInsuranceService.findByMedicalTest(medicalTest);
            result.retainAll(medicalTests);
        }

        if (!Objects.equals(smokerStatus, "all")) {
            List<LifeInsurance> smokers = lifeInsuranceService.findBySmokerStatus(smokerStatus);
            result.retainAll(smokers);
        }

        if (!Objects.equals(gender, "all")) {
            List<LifeInsurance> genders = lifeInsuranceService.findByGender(gender);
            result.retainAll(genders);
        }

        if (!Objects.equals(age, "all")) {
            List<LifeInsurance> ages = lifeInsuranceService.findByAge(age);
            result.retainAll(ages);
        }

        return result;
    }
}
