package com.accenture.itfactory.configurations;

public class ConstHtml {

    private static final String FORM_REGISTRATION=
            "<form action='registration' method='post'>" +
                    "<input type='text' name='login' placeholder='Login'>" +
                    "<input type='text' name='password' placeholder='Password'>" +
                    "<input type='text' name='name' placeholder='Name'>" +
                    "</form>";

    private static final String FORM_LOGIN=
            "<form action='login' method='get'>" +
                    "<input type='text' name='login' placeholder='Login'>" +
                    "<input type='text' name='password' placeholder='Password'>" +
                    "<button type='submit'>Login</button>" +
                    "</form>";

    private static final String FORM_ADMIN=
            "<form action='admin' method='post'>" +
                    "<button type='submit' name='questions'>Questions</button>" +
                    "<button type='submit' name='tests'>Tests</button>" +
                    "<button type='submit' name='logout'>LogOut</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS=
            "<form action='question' method='post'>" +
                    "<button type='submit' name='add'>Add Questions</button>" +
                    "<button type='submit' name='delete'>Delete Questions</button>" +
                    "<button type='submit' name='update'>Update Questions</button>" +
                    "<button type='submit' name='show'>Show Questions</button>" +
                    "<button type='submit' name='info'>Info Question</button>" +
                    "<button type='submit' name='search'>Search Question</button>" +
                    "<button type='submit' name='back'>Back</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_ADD=
            "<form action='question' method='post'>" +
                    "<input type='text' name='question' placeholder='Question'>" +
                    "<input type='text' name='type' placeholder='Type'>" +
                    "<input type='text' name='author' placeholder='Author'>" +
                    "<button type='submit' name='addQuestion'>Next</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_ADD_TYPE1=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer2' placeholder='Incorrect Answer'>" +
                    "<input type='text' name='answer3' placeholder='Incorrect Answer'>" +
                    "<button type='submit' name='type1'>Save</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_ADD_TYPE2=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer2' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer3' placeholder='Incorrect Answer'>" +
                    "<input type='text' name='answer4' placeholder='Incorrect Answer'>" +
                    "<button type='submit' name='type2'>Save</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_ADD_TYPE3=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<button type='submit' name='type3'>Save</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_DELETE=
            "<form action='question' method='post'>" +
                    "<input type='text' name='id' placeholder='Id'>" +
                    "<button type='submit' name='del'>Delete</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_SEARCH=
            "<form action='question' method='post'>" +
                    "<input type='text' name='text' placeholder='Question'>" +
                    "<button type='submit' name='view'>Search</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_INFO=
            "<form action='question' method='post'>" +
                    "<input type='text' name='id' placeholder='Id'>" +
                    "<button type='submit' name='infoQuestion'>Show</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_UPDATE=
            "<form action='question' method='post'>" +
                    "<input type='text' name='id' placeholder='Id'>" +
                    "<button type='submit' name='searchQuestion'>Search</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_UPDATE_QUESTION=
            "<form action='question' method='post'>" +
                    "<input type='text' name='name' placeholder='Question'>" +
                    "<input type='text' name='type' placeholder='Type'>" +
                    "<input type='text' name='author' placeholder='Author'>" +
                    "<button type='submit' name='next'>Next</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE1=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer2' placeholder='Incorrect Answer'>" +
                    "<input type='text' name='answer3' placeholder='Incorrect Answer'>" +
                    "<button type='submit' name='update1'>Update</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE2=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer2' placeholder='Correct Answer'>" +
                    "<input type='text' name='answer3' placeholder='Incorrect Answer'>" +
                    "<input type='text' name='answer4' placeholder='Incorrect Answer'>" +
                    "<button type='submit' name='update2'>Update</button>" +
                    "</form>";

    private static final String FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE3=
            "<form action='question' method='post'>" +
                    "<input type='text' name='answer1' placeholder='Correct Answer'>" +
                    "<button type='submit' name='update3'>Update</button>" +
                    "</form>";


    public static String getFormRegistration() {
        return FORM_REGISTRATION;
    }

    public static String getFormLogin() {
        return FORM_LOGIN;
    }

    public static String getFormAdmin() {
        return FORM_ADMIN;
    }

    public static String getFormAdminQuestions() {
        return FORM_ADMIN_QUESTIONS;
    }

    public static String getFormAdminQuestionsAdd() {
        return FORM_ADMIN_QUESTIONS_ADD;
    }

    public static String getFormAdminQuestionsAddType1() {
        return FORM_ADMIN_QUESTIONS_ADD_TYPE1;
    }

    public static String getFormAdminQuestionsAddType2() {
        return FORM_ADMIN_QUESTIONS_ADD_TYPE2;
    }

    public static String getFormAdminQuestionsAddType3() {
        return FORM_ADMIN_QUESTIONS_ADD_TYPE3;
    }

    public static String getFormAdminQuestionsDelete() {
        return FORM_ADMIN_QUESTIONS_DELETE;
    }

    public static String getFormAdminQuestionsSearch() {
        return FORM_ADMIN_QUESTIONS_SEARCH;
    }

    public static String getFormAdminQuestionsInfo() {
        return FORM_ADMIN_QUESTIONS_INFO;
    }

    public static String getFormAdminQuestionsUpdate() {
        return FORM_ADMIN_QUESTIONS_UPDATE;
    }

    public static String getFormAdminQuestionsUpdateQuestion() {
        return FORM_ADMIN_QUESTIONS_UPDATE_QUESTION;
    }

    public static String getFormAdminQuestionsUpdateQuestionType1() {
        return FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE1;
    }

    public static String getFormAdminQuestionsUpdateQuestionType2() {
        return FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE2;
    }

    public static String getFormAdminQuestionsUpdateQuestionType3() {
        return FORM_ADMIN_QUESTIONS_UPDATE_QUESTION_TYPE3;
    }
}
