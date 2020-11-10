package com.accenture.itfactory.servlets;

import com.accenture.itfactory.configurations.ConstHtml;
import com.accenture.itfactory.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<body>");
        pw.println(ConstHtml.getFormRegistration());
        pw.println("<p><a href='/webapp/main'>Back</a></p>");
        pw.println("</body>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String text=UserService.getInstance().register(login,password,name);
        PrintWriter pw =response.getWriter();
        if(text.equalsIgnoreCase("Вы зарегестрировались!")){
            pw.println("<body>");
            pw.println("<p>"+text+"</p>");
            pw.println(ConstHtml.getFormRegistration());
            pw.println("<a href='/webapp/main'>Back</a>");
            pw.println("</body>");
        }
        else{
            pw.println("<body>");
            pw.println("<p>"+text+"</p>");
            pw.println(ConstHtml.getFormRegistration());
            pw.println("<p><a href='/webapp/main'>Back</a></p>");
            pw.println("</body>");
        }
    }

}
