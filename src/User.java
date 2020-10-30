import java.io.*;

public class User implements UserService{
    String name;
    String role;
    String password;
    String login;

    //регистрация пользователя
    @Override
    public void register(String log, String data) {
        try {
            File file = new File("users.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] lines = line.split(" ");
                login=lines[0];
                if(log.equals(login)){
                    System.out.println("Пользователь с таким логином уже зарегестрирован");
                    return;
                }
                line = reader.readLine();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt",true));
            writer.write("\r\n");
            writer.write(data);
            reader.close();
            writer.close();
            System.out.println("Вы зарегестрировались");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Авторизация пользователя
    @Override
    public boolean authorized(String log, String pass) {
        try {
            File file = new File("users.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] lines = line.split(" ");
                login=lines[0];
                password=lines[1];
                if(log.equals(login)){
                    if (pass.equals(password)){
                        System.out.println("Вы авторизировались");
                        role=lines[3];
                        return true;
                    }
                    System.out.println("Неверный пароль");
                    return false;
                }
                line = reader.readLine();
            }
            System.out.println("Пользователя с таким логином нету");
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //получить роль пользователя
    public String getRole(){
        return role;
    }
}
