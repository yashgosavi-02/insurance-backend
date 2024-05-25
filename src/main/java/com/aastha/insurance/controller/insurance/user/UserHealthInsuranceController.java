package com.aastha.insurance.controller.insurance.user;


import com.aastha.insurance.entity.User;
import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.service.EmailService;
import com.aastha.insurance.service.UserService;
import com.aastha.insurance.service.insurance.HealthInsuranceService;
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
@RequestMapping("/insurance/health")
@CrossOrigin(origins = "http://localhost:5173")
public class UserHealthInsuranceController {

    private HealthInsuranceService healthInsuranceService;
    private EmailService emailService;
    private UserService userService;

    public UserHealthInsuranceController(HealthInsuranceService healthInsuranceService, EmailService emailService, UserService userService) {
        this.healthInsuranceService = healthInsuranceService;
        this.emailService = emailService;
        this.userService = userService;
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
    @PostMapping("/quote")
    public ResponseEntity<String> getQuote(@RequestBody Map<String,List<Integer>> quotes){
        System.out.println(quotes);
        List<HealthInsurance> healthInsurances=new ArrayList<>();
        List<Integer> ids = quotes.get("id");
        for(Integer id:ids){
            healthInsurances.add(healthInsuranceService.getOne(id));
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
        String subject = "Quotation for health insurance policies by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for health insurance is listed below :";
        for(HealthInsurance insurance: healthInsurances){
            body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Coverage Amount"+insurance.getCoverageAmount()+" Premium"+insurance.getPremium();
        }
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/5isNFXhXTTNXKWRr8";
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
        HealthInsurance insurance = healthInsuranceService.getOne(id);
        String subject = "Quotation for health insurance policy by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for health insurance is listed below :";
        body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Coverage Amount"+insurance.getCoverageAmount()+" Premium"+insurance.getPremium();
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/5isNFXhXTTNXKWRr8";
        body+="\n Your Details: \n"+" Name : "+user.getUserName()+
                "\n Email : "+user.getEmail()+
                "\n Phone No. : "+user.getPhone();
        emailService.sendMail(user.getEmail(),subject,body);
        return new ResponseEntity<>("Quotation request sent.", HttpStatus.OK);
    }
}
