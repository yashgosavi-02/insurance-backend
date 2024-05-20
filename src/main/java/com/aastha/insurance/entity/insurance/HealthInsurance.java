package com.aastha.insurance.entity.insurance;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "health")
public class HealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String company;
    private String coverageAmount;
    private String policyTerm;
    private String premium;
    private String chronicDisease;
    private String gender;
    private String age;
}
