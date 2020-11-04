import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class User implements UserService{

    Scanner scanner = new Scanner(System.in);
    String role;
    String name;

    //регистрация пользователя
    @Override
    public void register() {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        int id=0;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from users where login='"+login+"'");
            while (rs.next()){
                System.out.println("Такой логин уже зарегестрирован, введите другой");
                login = scanner.nextLine();
                rs = stmt.executeQuery("select * from users where login='"+login+"'");
            }
            System.out.println("Введите пароль");
            String password=scanner.nextLine();
            System.out.println("Введите имя");
            String name = scanner.nextLine();
            rs = stmt.executeQuery("select id from users");
            if(rs.next()){
                rs.last();
                id=rs.getInt(1);
                id++;
            }
            String sql = "insert into users(id,login,password,name,role) values("+id+",'"+login+"','"+password+"','"+name+"','user')";
            int count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }

    }


    //Авторизация пользователя
    @Override
    public boolean authorized() {
        System.out.println("Введите логин");
        String login=scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from users where login='"+login+"' and password='"+password+"'");
            if(rs.next()){
                System.out.println("Привет, "+rs.getString(4));
                role=rs.getString(5);
                name=rs.getString(4);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};

        }
        System.out.println("Неверные данные, попробуйте снова или зарегестрируйтесь");
        return false;
    }

    public String getRole(){
        return role;
    }

    public String getName(){
        return name;
    }
}