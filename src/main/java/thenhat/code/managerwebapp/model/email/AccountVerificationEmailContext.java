package thenhat.code.managerwebapp.model.email;

import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;
import thenhat.code.managerwebapp.model.Users;

@Data
public class AccountVerificationEmailContext extends AbstractEmailContext{

    //== field ==
    private String token;

    //== methods ==
    @Override
    public <T> void init(T context) {
        //== pass the customer info which binding in thymeleaf template ==
        Users customer = (Users) context;
        put("lastName", customer.getLastName());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("nhat0103st@gmail.com");
        setTo(customer.getEmailAddress());
    }
    public void setToken(String token){
        this.token = token;
        put("token", token);
    }
    public void buildVerificationUrl(String baseUrl, String token) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).path("register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
