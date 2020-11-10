package com.accenture.itfactory.services;

import com.accenture.itfactory.entity.Answer;

import java.util.ArrayList;

public class AnswerService {
    private static AnswerService instance = null;

    private AnswerService() { }

    public static AnswerService getInstance() {
        if(instance == null){
            synchronized (AnswerService.class) {
                if(instance == null){
                    instance = new AnswerService();
                }
            }
        }
        return instance;
    }

    public ArrayList<Answer> saveAnswer(int type, String[] answersUser){
        ArrayList<Answer> answers = new ArrayList<Answer>();
        Answer answer = new Answer();
        answer.setCorrect("correct");
        answer.setAnswer(answersUser[0]);
        answers.add(answer);
        if(type==1){
            for(int i=1;i<3;i++){
                answer = new Answer();
                answer.setCorrect("incorrect");
                answer.setAnswer(answersUser[i]);
                answers.add(answer);
            }
        }
        if(type==2){
            answer = new Answer();
            answer.setCorrect("correct");
            answer.setAnswer(answersUser[1]);
            answers.add(answer);
            for(int i=2;i<4;i++){
                answer = new Answer();
                answer.setCorrect("incorrect");
                answer.setAnswer(answersUser[i]);
                answers.add(answer);
            }
        }
        return answers;
    }
}
