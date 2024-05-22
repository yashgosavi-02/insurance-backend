package com.aastha.insurance.controller.insurance.user;

import com.aastha.insurance.entity.insurance.HomeInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.insurance.HomeInsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/insurance/home")
@CrossOrigin(origins = "http://localhost:5173")
public class UserHomeInsuranceController {

    private HomeInsuranceService homeInsuranceService;

    public UserHomeInsuranceController(HomeInsuranceService homeInsuranceService) {
        this.homeInsuranceService = homeInsuranceService;
    }

    @GetMapping("/filter")
    public List<HomeInsurance> sort(@RequestParam(value = "policyTerm", defaultValue = "all") String term,
                                    @RequestParam(value = "houseValue", defaultValue = "all") String houseValue,
                                    @RequestParam(value = "householdItemsValue", defaultValue = "all") String householdItemsValue,
                                    @RequestParam(value = "houseAge", defaultValue = "all") String houseAge){

        if (Objects.equals(term, "all") && Objects.equals(houseValue, "all") && Objects.equals(householdItemsValue, "all")
                && Objects.equals(houseAge, "all")) {
            return homeInsuranceService.getAll();
        }

        List<HomeInsurance> result = homeInsuranceService.getAll();

        if (!Objects.equals(term, "all")) {
            List<HomeInsurance> terms = homeInsuranceService.findByPolicyTerm(term);
            result.retainAll(terms);
        }

        if (!Objects.equals(houseValue, "all")) {
            List<HomeInsurance> houseValues = homeInsuranceService.findByHouseValue(houseValue);
            result.retainAll(houseValues);
        }

        if (!Objects.equals(householdItemsValue, "all")) {
            List<HomeInsurance> householdItemsValues = homeInsuranceService.findByHouseholdItemsValue(householdItemsValue);
            result.retainAll(householdItemsValues);
        }

        if (!Objects.equals(houseAge, "all")) {
            List<HomeInsurance> houseAges = homeInsuranceService.findByHouseAge(houseAge);
            result.retainAll(houseAges);
        }
        return result;
    }
}
