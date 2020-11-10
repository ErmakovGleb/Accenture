package com.accenture.itfactory.filters;

import com.accenture.itfactory.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "adminFilter",urlPatterns = {"/admin","/admin/*"})
public class AdminFilter implements Filter {


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("user");
            if(user != null) {
                if(user.getRole().equalsIgnoreCase("admin")){
                    servletResponse.setContentType("text/html;charset=UTF-8");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        } else {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            res.sendRedirect("/webapp/login");
        }

    }

    @Override
    public void destroy() {
    }
}


