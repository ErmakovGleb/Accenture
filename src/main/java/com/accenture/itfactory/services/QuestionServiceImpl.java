package com.accenture.itfactory.services;

import com.accenture.itfactory.access.DataRepository;
import com.accenture.itfactory.entity.Answer;
import com.accenture.itfactory.configurations.ConstDB;
import com.accenture.itfactory.entity.Question;

import java.util.ArrayList;

public class QuestionServiceImpl implements QuestionService {
    private static QuestionServiceImpl instance = null;

    private QuestionServiceImpl() { }

    public static QuestionServiceImpl getInstance() {
        if(instance == null){
            synchronized (QuestionServiceImpl.class) {
                if(instance == null){
                    instance = new QuestionServiceImpl();
                }
            }
        }
        return instance;
    }

    public String addQuestion(Question question, ArrayList<Answer> answers){
        int idQuestion = DataRepository.getInstance().getId(ConstDB.getQuestionTable());
        int idAnswer= DataRepository.getInstance().getId(ConstDB.getAnswerTable());
        String sql;
        sql = "insert into questions(id,name,type,author) values("+idQuestion+",'"+question.getName()+"',"+question.getType()+",'"+question.getAuthor()+"')";
        DataRepository.getInstance().request(sql);
        for (Answer answer : answers) {
            sql="insert into answers(id,question,answer,correct) values("+idAnswer+","+idQuestion+",'"+answer.getAnswer()+"','"+answer.getCorrect()+"')";
            DataRepository.getInstance().request(sql);
            idAnswer++;
        }
        return "Вопрос добавлен";
    }

    public String deleteQuestion(int id){
        boolean complete;
        String sql;
        sql="delete from questions where id = "+id;
        complete= DataRepository.getInstance().request(sql);
        if(!complete){
            return "Вопрос не найден";
        }
        sql="delete from answers where question = "+id;
        DataRepository.getInstance().request(sql);
        return "Вопрос удален";
    }

    public String showQuestions(){
        String sql="select name from questions";
        ArrayList<String> list = DataRepository.getInstance().select(sql);
        String questions="";
        for (String str : list){
            questions=questions+"<p>"+str+"</p>";
        }
        return questions;
    }

    public String showInfo(int id){
        String question="";
        String sql="select name from questions where id="+id;
        ArrayList<String> list = DataRepository.getInstance().select(sql);
        question=question+"<p>Вопрос: "+list.get(0)+" ";
        sql="select type from questions where id="+id;
        list = DataRepository.getInstance().select(sql);
        switch (list.get(0)){
            case "1 ": question=question+" Тип: Вопрос с единственным ответом "; break;
            case "2 ": question=question+" Тип: Вопрос с множеством ответов "; break;
            case "3 ": question=question+" Тип: Открытый вопрос "; break;
        }
        sql="select author from questions where id="+id;
        list = DataRepository.getInstance().select(sql);
        question=question+" Автор: "+list.get(0)+"</p>";
        sql="select answer from answers where question="+id;
        list = DataRepository.getInstance().select(sql);
        for (String str : list){
            question=question+"<p>Ответ: "+str+"</p>";
        }
        return question;
    }

    public String search(String text){
        String sql="select name from questions where name LIKE '%"+text+"%'";
        String questions="";
        ArrayList<String> list = DataRepository.getInstance().select(sql);
        for (String str : list){
            questions=questions+"<p>"+str+"</p>";
        }
        return questions;
    }

    public boolean isExist(String id){
        boolean exist=false;
        String sql="select * from questions where id="+id;
        ArrayList<String> list = DataRepository.getInstance().select(sql);
        if(!list.isEmpty()){
            exist=true;
        }
        return exist;
    }

    public String update(Question question, ArrayList<Answer> answers){
        int idQuestion = question.getId();
        String sql;
        sql = "update questions set name='"+question.getName()+"', type="+question.getType()+", author='"+question.getAuthor()+"' where id="+idQuestion+"";
        DataRepository.getInstance().update(sql);
        sql = "delete from answers where question = "+idQuestion;
        DataRepository.getInstance().request(sql);
        int idAnswer=DataRepository.getInstance().getId(ConstDB.getAnswerTable());
        for (Answer answer : answers){
            sql="insert into answers(id,question,answer,correct) values("+idAnswer+","+idQuestion+",'"+answer.getAnswer()+"','"+answer.getCorrect()+"')";
            DataRepository.getInstance().request(sql);
            idAnswer++;
        }
        return "Вопрос обнавлен";
    }

}
