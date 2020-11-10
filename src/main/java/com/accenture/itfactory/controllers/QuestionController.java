package com.accenture.itfactory.controllers;

import com.accenture.itfactory.entity.Answer;
import com.accenture.itfactory.entity.Question;
import com.accenture.itfactory.services.QuestionServiceImpl;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("/admin")
@Consumes("application/json")
@Produces("application/json")
public class QuestionController {

    @GET
    @Path("/question/{id}")
    public String getById(@PathParam("id") String id) {
        String question=QuestionServiceImpl.getInstance().showInfo(Integer.parseInt(id));
        return question;
    }

    @DELETE
    @Path("/question/{id}")
    public String Delete(@PathParam("id") String id) {
        String str=QuestionServiceImpl.getInstance().deleteQuestion(Integer.parseInt(id));
        return str;
    }

    @POST
    @Path("/question/view")
    public String View(@QueryParam("text") String text) {
        String questions;
        questions=QuestionServiceImpl.getInstance().search(text);
        return questions;
    }

    @POST
    @Path("/question")
    public String View(@QueryParam("question") Question question, @QueryParam("answers") ArrayList<Answer> answers) {
        String create=QuestionServiceImpl.getInstance().addQuestion(question,answers);
        return create;
    }

}
