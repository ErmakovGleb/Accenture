package com.accenture.itfactory.filters;

import com.accenture.itfactory.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/login","/register","/main"})
public class LoginFilter implements Filter {


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if(session != null) {
            User user = (User)session.getAttribute("user");
            if(user != null) {
                if(user.getRole().equalsIgnoreCase("admin")){
                    res.sendRedirect("/webapp/admin");
                }
                if(user.getRole().equalsIgnoreCase("user")){
                    res.sendRedirect("/webapp/user");
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
    }
}