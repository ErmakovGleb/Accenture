package com.accenture.itfactory.services;

import com.accenture.itfactory.entity.Answer;
import com.accenture.itfactory.entity.Question;

import java.util.ArrayList;

public interface QuestionService {
    String addQuestion(Question question, ArrayList<Answer> answers);
    String deleteQuestion(int id);
    String showQuestions();
}
