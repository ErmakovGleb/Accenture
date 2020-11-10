package com.accenture.itfactory.servlets;

import com.accenture.itfactory.configurations.ConstHtml;
import com.accenture.itfactory.entity.Answer;
import com.accenture.itfactory.entity.Question;
import com.accenture.itfactory.services.AnswerService;
import com.accenture.itfactory.services.QuestionService;
import com.accenture.itfactory.services.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "adminQuestionsServlet", urlPatterns = "/admin/question")
public class AdminQuestionsServlet extends HttpServlet {
    Question question = new Question();
    String idQuestion="";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<body>");
        pw.println(ConstHtml.getFormAdminQuestions());
        pw.println("</body>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //add
        String add = request.getParameter("add");
        String addQuestion = request.getParameter("addQuestion");
        String quest = request.getParameter("question");
        String author = request.getParameter("author");
        String type = request.getParameter("type");
        String type1 = request.getParameter("type1");
        String type2 = request.getParameter("type2");
        String type3 = request.getParameter("type3");

        //delete
        String delete = request.getParameter("delete");
        String del = request.getParameter("del");

        //update
        String update = request.getParameter("update");
        String searchQuestion = request.getParameter("searchQuestion");
        String next = request.getParameter("next");
        String update1 = request.getParameter("update1");
        String update2 = request.getParameter("update2");
        String update3 = request.getParameter("update3");

        //show
        String show = request.getParameter("show");

        //search
        String search = request.getParameter("search");
        String view = request.getParameter("view");
        String text = request.getParameter("text");

        //info
        String info = request.getParameter("info");
        String infoQuestion = request.getParameter("infoQuestion");

        //back
        String back = request.getParameter("back");

        PrintWriter pw = response.getWriter();

        pw.println("<body>");
        pw.println(ConstHtml.getFormAdminQuestions());
        if(add!=null){
            pw.println("<p>Типы вопросов: 1 - Вопрос с одним ответов, 2 - Вопрос со множестов ответов, 3 - Открытый вопрос</p>");
            pw.println(ConstHtml.getFormAdminQuestionsAdd());
            pw.println("</form>");
        }
        if(addQuestion!=null){
            question=new Question();
            question.setName(quest);
            question.setType(Integer.parseInt(type));
            question.setAuthor(author);
            if(type.equalsIgnoreCase("1")){
                pw.println(ConstHtml.getFormAdminQuestionsAddType1());
            }
            if(type.equalsIgnoreCase("2")){
                pw.println(ConstHtml.getFormAdminQuestionsAddType2());
            }
            if(type.equalsIgnoreCase("3")){
                pw.println(ConstHtml.getFormAdminQuestionsAddType3());
            }
        }
        if(type1!=null){
            String[] answersUser = new String[3];
            answersUser[0]=request.getParameter("answer1");
            answersUser[1]=request.getParameter("answer2");
            answersUser[2]=request.getParameter("answer3");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(1,answersUser);
            String create=QuestionServiceImpl.getInstance().addQuestion(question,answers);
            pw.println("<p>"+create+"</p>");
        }
        if(type2!=null){
            String[] answersUser = new String[4];
            answersUser[0]=request.getParameter("answer1");
            answersUser[1]=request.getParameter("answer2");
            answersUser[2]=request.getParameter("answer3");
            answersUser[3]=request.getParameter("answer4");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(2,answersUser);
            String create=QuestionServiceImpl.getInstance().addQuestion(question,answers);
            pw.println("<p>"+create+"</p>");
        }
        if(type3!=null){
            String[] answersUser = new String[1];
            answersUser[0]=request.getParameter("answer1");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(3,answersUser);;
            String create=QuestionServiceImpl.getInstance().addQuestion(question,answers);
            pw.println("<p>"+create+"</p>");
        }
        if(del!=null){
            String id = request.getParameter("id");
            String str= QuestionServiceImpl.getInstance().deleteQuestion(Integer.parseInt(id));
            pw.println(ConstHtml.getFormAdminQuestionsDelete());
            pw.println("<p>"+str+"</p>");
        }
        if(delete!=null){
            pw.println(ConstHtml.getFormAdminQuestionsDelete());
        }
        if(update!=null){
            pw.println(ConstHtml.getFormAdminQuestionsUpdate());
        }
        if(searchQuestion!=null){
            pw.println(ConstHtml.getFormAdminQuestionsUpdate());
            pw.println(ConstHtml.getFormAdminQuestionsUpdateQuestion());
            idQuestion = request.getParameter("id");
        }
        if(next!=null){
            pw.println(ConstHtml.getFormAdminQuestionsUpdate());
            pw.println(ConstHtml.getFormAdminQuestionsUpdateQuestion());
            if(!QuestionServiceImpl.getInstance().isExist(idQuestion)){
                pw.println("<p>Такого вопроса нету</p>");
            }else{
                question = new Question();
                question.setId(Integer.parseInt(idQuestion));
                question.setAuthor(request.getParameter("author"));
                question.setName(request.getParameter("name"));
                question.setType(Integer.parseInt(request.getParameter("type")));
                switch (question.getType()){
                    case 1: pw.println(ConstHtml.getFormAdminQuestionsUpdateQuestionType1()); break;
                    case 2: pw.println(ConstHtml.getFormAdminQuestionsUpdateQuestionType2()); break;
                    case 3: pw.println(ConstHtml.getFormAdminQuestionsUpdateQuestionType3()); break;
                }
            }

        }
        if(update1!=null){
            String[] answersUser = new String[3];
            answersUser[0]=request.getParameter("answer1");
            answersUser[1]=request.getParameter("answer2");
            answersUser[2]=request.getParameter("answer3");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(1,answersUser);
            String isUpdate=QuestionServiceImpl.getInstance().update(question,answers);
            pw.println("<p>"+isUpdate+"</p>");
        }
        if(update2!=null){
            String[] answersUser = new String[4];
            answersUser[0]=request.getParameter("answer1");
            answersUser[1]=request.getParameter("answer2");
            answersUser[2]=request.getParameter("answer3");
            answersUser[2]=request.getParameter("answer4");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(2,answersUser);
            String isUpdate=QuestionServiceImpl.getInstance().update(question,answers);
            pw.println("<p>"+isUpdate+"</p>");
        }
        if(update3!=null){
            String[] answersUser = new String[1];
            answersUser[0]=request.getParameter("answer1");
            ArrayList<Answer> answers = AnswerService.getInstance().saveAnswer(3,answersUser);
            String isUpdate=QuestionServiceImpl.getInstance().update(question,answers);
            pw.println("<p>"+isUpdate+"</p>");
        }
        if(show!=null){
            pw.println("<div style='height:100px; overflow: auto'>");
            pw.println(QuestionServiceImpl.getInstance().showQuestions());
            pw.println("</div>");
        }
        if(search!=null){
            pw.println(ConstHtml.getFormAdminQuestionsSearch());
        }

        if(view!=null){
            String questions;
            questions=QuestionServiceImpl.getInstance().search(text);
            pw.println(ConstHtml.getFormAdminQuestionsSearch());
            pw.println(questions);
        }
        if(info!=null){
            pw.println(ConstHtml.getFormAdminQuestionsInfo());
        }
        if(infoQuestion!=null){
            String id = request.getParameter("id");
            String questionInfo=QuestionServiceImpl.getInstance().showInfo(Integer.parseInt(id));
            pw.println(ConstHtml.getFormAdminQuestionsInfo());
            pw.println("<p>"+questionInfo+"</p>");
        }
        if(back!=null){
            response.sendRedirect("/webapp/admin");
        }
        pw.println("</body>");
    }
}
