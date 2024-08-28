package com.project.bank_application.service;

import com.project.bank_application.dtos.EmailDetails;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);

}
