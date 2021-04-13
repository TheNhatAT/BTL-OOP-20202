package thenhat.code.managerwebapp.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component("testFilter")
//== custom filter class ==
public class AuthenticationFilter implements Filter {

    //== methods ==
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Authentication.....");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
