package com.aastha.insurance.entity.insurance;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "home")
public class HomeInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String houseValue;
    private String householdItemsValue;
    private String policyTerm;
    private String houseAge;
    private String company;
    private String premium;
}
