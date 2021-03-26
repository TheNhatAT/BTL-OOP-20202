package thenhat.code.managerwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //use to add home page
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //use to format the entity(trường hợp class trong class)
    }
}
