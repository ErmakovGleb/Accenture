import java.sql.*;
import java.util.Scanner;

public class QuestionServiceImpl implements QuestionService{
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

    public void addQuestion(){
        Question question = new Question();
        String nameQuestion = question.getQuestion();
        String typeQuestion = question.getType();
        String authorQuestion = "author";
        int id=0;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id from questions");
            if(rs.next()){
                rs.last();
                id=rs.getInt(1);
                id++;
            }
            rs = stmt.executeQuery("select name from questions where name='"+nameQuestion+"'");
            if(rs.next()){
                System.out.println("Такой вопрос уже есть");
                return;
            }
            String sql = "insert into QUESTIONS(id,name,type,author) values("+id+",'"+nameQuestion+"','"+typeQuestion+"','"+authorQuestion+"')";
            int count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        newAnswers(typeQuestion,id);
    }

    public void deleteQuestion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите вопрос для удаления");
        String question = scanner.nextLine();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            con.setAutoCommit(false);
            rs = stmt.executeQuery("select * from questions where name='"+question+"'");
            if(rs.next()){
                try {
                    String id=rs.getString(1);
                    stmt.executeUpdate("delete from questions where id = "+id);
                    stmt.executeUpdate("delete from answers where question = "+id);
                    con.commit();
                    System.out.println("Вопрос удален");
                    return;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        System.out.println("Вопрос не найден");
    }

    public void newAnswers(String typeQuestion, int question){
        int id=0;
        Question qust= new Question();
        String answer;
        String correct;
        if(typeQuestion.equalsIgnoreCase("1")){
            for(int i=0;i<3;i++){
                correct="incorrect";
                if(i==1){
                    correct="correct";
                }
                answer=qust.getAnswerFirstType(i);
                String sql="insert into answers(id,question,answer,correct) values("+id+","+question+",'"+answer+"','"+correct+"')";
                addAnswer(answer,correct,question);
            }
        }
        if(typeQuestion.equalsIgnoreCase("2")){
            for(int i=0;i<4;i++){
                correct="incorrect";
                if(i==1||i==2){
                    correct="correct";
                }
                answer=qust.getAnswerSecondType(i);
                String sql="insert into answers(id,question,answer,correct) values("+id+","+question+",'"+answer+"','"+correct+"')";
                addAnswer(answer,correct,question);
            }
        }
        if(typeQuestion.equalsIgnoreCase("3")){
            correct="correct";
            answer=qust.getAnswerThirdType();
            addAnswer(answer,correct,question);
        }
    }

    public void addAnswer(String answer,String correct, int question){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int id=0;
        String sql;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id from answers");
            if(rs.next()){
                rs.last();
                id=rs.getInt(1);
                id++;
            }
            sql="insert into answers(id,question,answer,correct) values("+id+","+question+",'"+answer+"','"+correct+"')";
            int count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
    }

    public void showQuestions(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select name from questions");
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

    public void showInfo(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from questions");
            while (rs.next()){
                System.out.println("Название вопроса:");
                System.out.println(rs.getString(2)+"\r\n");
                System.out.println("Тип вопроса:");
                switch (rs.getInt(3)){
                    case 1:
                        System.out.println("Вопрос с единственным правильным ответом\r\n") ;break;
                    case 2:
                        System.out.println("Вопрос с множеством правильных ответов\r\n") ;break;
                    case 3:
                        System.out.println("Открытый вопрос\r\n") ;break;
                }
                System.out.println("Автор:");
                System.out.println(rs.getString(4)+"\r\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
    }

}
