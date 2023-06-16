package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.EmailDetails;

public interface IEmailService {
    
    void sendSimpleMail(EmailDetails details);
}
