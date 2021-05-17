package thenhat.code.managerwebapp;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManagerwebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerwebappApplication.class, args);
    }
    //== http://localhost:8080/swagger-ui-custom.html ==/
    @Bean
    public OpenAPI customOpenAPI(@Value("1.5.2") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("ManagerSchedule API").version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
