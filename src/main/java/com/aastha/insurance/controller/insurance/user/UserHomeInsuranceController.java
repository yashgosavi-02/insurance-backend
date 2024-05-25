package com.aastha.insurance.controller.insurance.user;

import com.aastha.insurance.entity.User;
import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import com.aastha.insurance.service.EmailService;
import com.aastha.insurance.service.UserService;
import com.aastha.insurance.service.insurance.HomeInsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/insurance/home")
@CrossOrigin(origins = "http://localhost:5173")
public class UserHomeInsuranceController {

    private HomeInsuranceService homeInsuranceService;

    private UserService userService;

    private EmailService emailService;

    public UserHomeInsuranceController(HomeInsuranceService homeInsuranceService, UserService userService, EmailService emailService) {
        this.homeInsuranceService = homeInsuranceService;
        this.userService = userService;
        this.emailService = emailService;
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
    @PostMapping("/quote")
    public ResponseEntity<String> getQuote(@RequestBody Map<String,List<Integer>> quotes){
        List<HomeInsurance> homeInsurances=new ArrayList<>();
        List<Integer> ids = quotes.get("id");
        for(Integer id:ids){
            homeInsurances.add(homeInsuranceService.getOne(id));
        }
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            user = userService.findByEmail(username);
        }
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        String subject = "Quotation for home insurance policies by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for home insurance is listed below :";
        for(HomeInsurance insurance: homeInsurances){
            body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Premium"+insurance.getPremium();
        }
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/oR9H16nVJYNiL2Fv8";
        body+="\n Your Details: \n"+" Name : "+user.getUserName()+
                "\n Email : "+user.getEmail()+
                "\n Phone No. : "+user.getPhone();
        emailService.sendMail(user.getEmail(),subject,body);
        return new ResponseEntity<>("Quotation request sent.", HttpStatus.OK);
    }
    @GetMapping("/quote/{id}")
    public ResponseEntity<String> getQuoteById(@PathVariable Integer id){
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            user = userService.findByEmail(username);
        }
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        HomeInsurance insurance = homeInsuranceService.getOne(id);
        String subject = "Quotation for home insurance policy by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for home insurance is listed below :";
        body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Premium"+insurance.getPremium();
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/oR9H16nVJYNiL2Fv8";
        body+="\n Your Details: \n"+" Name : "+user.getUserName()+
                "\n Email : "+user.getEmail()+
                "\n Phone No. : "+user.getPhone();
        emailService.sendMail(user.getEmail(),subject,body);
        return new ResponseEntity<>("Quotation request sent.", HttpStatus.OK);
    }
}
