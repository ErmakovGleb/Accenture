import java.sql.*;
import java.util.Scanner;

public class QuestionRepositoryImpl implements QuestionRepository {
    private static QuestionRepositoryImpl instance = null;

    private QuestionRepositoryImpl() {
    }

    public static QuestionRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (QuestionRepositoryImpl.class) {
                if (instance == null) {
                    instance = new QuestionRepositoryImpl();
                }
            }
        }
        return instance;
    }

    public void createTest(){
        Test test=new Test();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        String name;
        int id=0;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id from tests");
            if(rs.next()){
                rs.last();
                id=rs.getInt(1);
                id++;
            }
            name=test.getName();
            rs = stmt.executeQuery("select * from tests where name='"+name+"'");
            if(rs.next()){
                System.out.println("Тест с таким именем уже есть");
                return;
            }
            String sql = "insert into tests(id,name) values("+id+",'"+name+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        addQuestions(id);
        System.out.println("Тест создан");
    }

    public void addQuestions(int testId){
        Test test = new Test();
        int countQuestions=test.getCountQuestions();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int id=0;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id from testsquestions");
            if(rs.next()){
                rs.last();
                id=rs.getInt(1);
                id++;
            }
            for(int i=0;i<countQuestions;i++){
                int questionId=getQuestion();
                if(questionId==-1){
                   i--;
                   continue;
                }
                String sql = "insert into testsquestions(id,test,question) values("+id+","+testId+","+questionId+")";
                stmt.executeUpdate(sql);
                id++;
                System.out.println("Вопрос добавлен");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }

    }

    public int getQuestion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите вопрос из списка вопросов для добавления в тест");
        String question = scanner.nextLine();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int id;
        String sql;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id from questions where name='"+question+"'");
            while (!rs.next()){
                System.out.println("Такого вопроса нету, введите существующий вопрос из списка вопросов");
                question=scanner.nextLine();
                rs = stmt.executeQuery("select id from questions where name='"+question+"'");
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return -1;
    }

    public void showTests(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select name from tests");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
    }

    //Прохождение теста
    public void passingTest(){
        int idTest=existTest();
        int countCorrectAnswers;
        while (idTest==-1){
            idTest=existTest();
        }
        int countQuestion=getCountQuestionsInTest(idTest);
        countCorrectAnswers=outputQuestions(countQuestion,idTest);
        float percent=(float)countCorrectAnswers/(float)countQuestion*100;
        System.out.println("Количество правильных ответов: "+countCorrectAnswers+"\r\nПроцент прохождения теста: "+percent);
    }

    public int existTest(){
        Connection con = null;
        Statement stmt = null;
        PassingTest passing = new PassingTest();
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            String name=passing.selectTest();
            rs = stmt.executeQuery("select id from tests where name='"+name+"'");
            while (!rs.next()){
                System.out.println("Такого теста нету, введите тест из спика тестов");
                name=passing.selectTest();
                rs = stmt.executeQuery("select id from tests where name='"+name+"'");
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return -1;
    }

    public int getCountQuestionsInTest(int idTest){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int counter=0;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from testsquestions where test="+idTest);
            while (rs.next()){
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return counter;
    }

    public int outputQuestions(int countQuestion, int idTest){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int correctAnswer=0;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select question from testsquestions where test="+idTest);
            int idQuestion;
            while (rs.next()){
                idQuestion=rs.getInt(1);
                correctAnswer=showQuestion(idQuestion,correctAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return correctAnswer;
    }

    public int showQuestion(int idQuestion, int correctAnswer){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int typeQuestion;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select name,type from questions where id="+idQuestion);
            rs.next();
            System.out.println(rs.getString(1));
            typeQuestion=rs.getInt(2);
            correctAnswer=showAnswers(idQuestion,typeQuestion,correctAnswer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return correctAnswer;
    }

    public int showAnswers(int idQuestion, int typeQuestion, int correctAnswer){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int counter=1;
        String answer="";
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select answer,correct from answers where question="+idQuestion);
            while (rs.next()){
                if(typeQuestion==1||typeQuestion==2){
                    System.out.println(counter+") "+rs.getString(1));
                    if(rs.getString(2).equalsIgnoreCase("correct"))
                    answer=answer+Integer.toString(counter);
                }
                if(typeQuestion==3){
                    answer=rs.getString(1);
                }
                counter++;
            }
            correctAnswer=getAnswer(typeQuestion,answer, correctAnswer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return correctAnswer;
    }

    public int getAnswer(int typeQuestion, String answer, int correctAnswer){
        Scanner scanner = new Scanner(System.in);
        if(typeQuestion==1){
            System.out.println("Выберите ответ, введите номер этого ответа:");
        }
        if(typeQuestion==2){
            System.out.println("Выберите оветы, напишите номер этих ответов в строчку");
        }
        if (typeQuestion==3){
            System.out.println("Введите ваш ответ");
        }
        String userAnswer=scanner.nextLine();
        if(userAnswer.equalsIgnoreCase(answer)){
            correctAnswer++;
        }
        return correctAnswer;
    }

}