package thenhat.code.managerwebapp.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

//== config DelegatingFilterProxy via Java Config ==
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //== methods ==

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    //== config Filter via Java Configuration ==
    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("testFilter");
        return new Filter[]{delegatingFilterProxy};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}
