package com.aastha.insurance.service;

public interface EmailService {
    void sendMail(String to, String subject, String body);
}
