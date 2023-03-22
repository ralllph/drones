package com.task.dronetask.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.task.dronetask.exception.UserNotFoundException;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//This filter is used in the scenario to check exception before request get to authentication filter
//Remember that the filters are called before dispatcher servlet which is why you cant create those custom exceptions like DroneNot Foiund
//the once per request is guaranteed to run only once per request
public class CheckExceptionFilter extends OncePerRequestFilter {
    @Override
    //the doFilter internal is overridden it helps to allow you point the filter chain to which filter to go next
    //filters always need pointers to which one to go next when created
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //this try catch block is saying try to go to the next filter and if you get a runtime there execute this catch
        try{
            //.doFilter passes the request and response to the next filter which could be authentication filter or another filter
            filterChain.doFilter(request,response);
        }
        //always remember the exception priority. the more generic ones goo last
        //remember any error thrown within a filter is caught in this class. So user impl throwing an error would get caught here
        catch(UserNotFoundException e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("USER NOT FOUND");    // send a bad request texxt
            response.getWriter().flush();
        }
        catch(JWTVerificationException E){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT NOT VALID");    // send a bad request texxt
            response.getWriter().flush();
        }
        catch(BadCredentialsException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Wrong password");    // send a bad request texxt
            response.getWriter().flush();
        }
    }
}
