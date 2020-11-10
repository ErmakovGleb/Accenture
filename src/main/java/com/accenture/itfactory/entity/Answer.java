package com.accenture.itfactory.entity;

public class Answer {
    int id;
    int question;
    String answer;
    String correct;

    public int getId() {
        return id;
    }

    public int getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
