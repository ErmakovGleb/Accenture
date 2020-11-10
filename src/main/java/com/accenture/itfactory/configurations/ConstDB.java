package com.accenture.itfactory.configurations;

public class ConstDB {
    //tables
    private static final String USER_TABLE = "users";
    private static final String QUESTION_TABLE = "questions";
    private static final String ANSWER_TABLE = "answers";
    private static final String TEST_TABLE = "tests";
    private static final String TEST_QUESTION_TABLE = "testsquestions";

    //users fields
    private static final String USER_ID = "id";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";
    private static final String USER_ROLE = "role";

    //question fields
    private static final String QUESTION_ID = "id";
    private static final String QUESTION_NAME = "name";
    private static final String QUESTION_TYPE = "type";
    private static final String QUESTION_AUTHOR = "author";

    //answer fields
    private static final String ANSWER_ID = "id";
    private static final String ANSWER_QUESTION = "question";
    private static final String ANSWER_ANSWER = "answer";
    private static final String ANSWER_CORRECT = "correct";

    //test fields
    private static final String TEST_ID = "id";
    private static final String TEST_NAME = "name";

    //test-question fields
    private static final String TEST_QUESTION_ID = "id";
    private static final String TEST_QUESTION_TEST = "test";
    private static final String TEST_QUESTION_QUESTION = "question";

    public static String getUserTable() {
        return USER_TABLE;
    }

    public static String getQuestionTable() {
        return QUESTION_TABLE;
    }

    public static String getAnswerTable() {
        return ANSWER_TABLE;
    }

    public static String getTestTable() {
        return TEST_TABLE;
    }

    public static String getTestQuestionTable() {
        return TEST_QUESTION_TABLE;
    }

    public static String getUserId() {
        return USER_ID;
    }

    public static String getUserLogin() {
        return USER_LOGIN;
    }

    public static String getUserPassword() {
        return USER_PASSWORD;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static String getUserRole() {
        return USER_ROLE;
    }

    public static String getQuestionId() {
        return QUESTION_ID;
    }

    public static String getQuestionName() {
        return QUESTION_NAME;
    }

    public static String getQuestionType() {
        return QUESTION_TYPE;
    }

    public static String getQuestionAuthor() {
        return QUESTION_AUTHOR;
    }

    public static String getAnswerId() {
        return ANSWER_ID;
    }

    public static String getAnswerQuestion() {
        return ANSWER_QUESTION;
    }

    public static String getAnswerAnswer() {
        return ANSWER_ANSWER;
    }

    public static String getAnswerCorrect() {
        return ANSWER_CORRECT;
    }

    public static String getTestId() {
        return TEST_ID;
    }

    public static String getTestName() {
        return TEST_NAME;
    }

    public static String getTestQuestionId() {
        return TEST_QUESTION_ID;
    }

    public static String getTestQuestionTest() {
        return TEST_QUESTION_TEST;
    }

    public static String getTestQuestionQuestion() {
        return TEST_QUESTION_QUESTION;
    }
}
