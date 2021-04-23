package thenhat.code.managerwebapp.service.email;

import thenhat.code.managerwebapp.model.email.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(AbstractEmailContext email) throws MessagingException;
}
