package com.aastha.insurance.controller.insurance.user;

import com.aastha.insurance.entity.User;
import com.aastha.insurance.entity.insurance.HealthInsurance;
import com.aastha.insurance.entity.insurance.HomeInsurance;
import com.aastha.insurance.entity.insurance.LifeInsurance;
import com.aastha.insurance.service.EmailService;
import com.aastha.insurance.service.UserService;
import com.aastha.insurance.service.insurance.LifeInsuranceService;
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
@RequestMapping("/insurance/life")
@CrossOrigin(origins = "http://localhost:5173")
public class UserLifeInsuranceController {

    private LifeInsuranceService lifeInsuranceService;

    private UserService userService;

    private EmailService emailService;

    public UserLifeInsuranceController(LifeInsuranceService lifeInsuranceService, UserService userService, EmailService emailService) {
        this.lifeInsuranceService = lifeInsuranceService;
        this.userService = userService;
        this.emailService = emailService;
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
    @PostMapping("/quote")
    public ResponseEntity<String> getQuote(@RequestBody Map<String,List<Integer>> quotes){
        List<LifeInsurance> lifeInsurances=new ArrayList<>();
        List<Integer> ids = quotes.get("id");
        for(Integer id:ids){
            lifeInsurances.add(lifeInsuranceService.getOne(id));
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
        String subject = "Quotation for life insurance policies by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for life insurance is listed below :";
        for(LifeInsurance insurance: lifeInsurances){
            body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Premium"+insurance.getPremium();
        }
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/KbQFQaoX89QMhKQd8";
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
        LifeInsurance insurance = lifeInsuranceService.getOne(id);
        String subject = "Quotation for life insurance policy by Aastha Insurance";
        String body = "Dear "+user.getUserName()+" your quotation summary for life insurance is listed below :";
        body+="\n- Company: "+insurance.getCompany()+" Policy Term"+insurance.getPolicyTerm()+" Coverage Amount"+insurance.getCoverageAmount()+" Premium"+insurance.getPremium();
        body+="\n Upload the required documents in the below link :\n"+"https://forms.gle/KbQFQaoX89QMhKQd8";
        body+="\n Your Details: \n"+" Name : "+user.getUserName()+
                "\n Email : "+user.getEmail()+
                "\n Phone No. : "+user.getPhone();
        emailService.sendMail(user.getEmail(),subject,body);
        return new ResponseEntity<>("Quotation request sent.", HttpStatus.OK);
    }

}
