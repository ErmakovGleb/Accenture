package com.accenture.itfactory.servlets;

import com.accenture.itfactory.configurations.ConstHtml;
import com.accenture.itfactory.entity.User;
import com.accenture.itfactory.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        HttpSession session;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Integer idUser=UserService.getInstance().authorized(login,password);

        pw.println("<body>");
        if(idUser!=-1){
            session = req.getSession();
            User user = (User)session.getAttribute("user");
            if(user==null){
                user = new User();
                user.setRole(UserService.getInstance().getRole(idUser));
                user.setName(UserService.getInstance().getName(idUser));
                user.setLogin(login);
                session.setAttribute("user", user);
                if (user.getRole().equalsIgnoreCase("admin")){
                    resp.sendRedirect("/webapp/admin");
                }else{
                    resp.sendRedirect("/webapp/user");
                }
            }
        }
        if(idUser==-1){
            if(login!=null) {
                pw.println("<p>Incorrect login or password</p>");
            }
        }
        pw.println(ConstHtml.getFormLogin());
        pw.println("<a href='/webapp/main'>Back</a>");
        pw.println("</body>");
    }
}
