package com.accenture.itfactory.servlets;

import com.accenture.itfactory.configurations.ConstHtml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<body>");
        pw.println(ConstHtml.getFormAdmin());
        pw.println("</body>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questions = request.getParameter("questions");
        String tests = request.getParameter("tests");
        String logout = request.getParameter("logout");
        if(questions!=null){
            response.sendRedirect("/webapp/admin/question");
        }
        if(tests!=null){
            response.sendRedirect("/webapp/admin/tests");
        }
        if(logout!=null){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession(false);
            session.invalidate();
            response.sendRedirect("/webapp/main");
        }
    }

}