import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    String name;
    String question;
    int countQuestions;
    List<String> questions = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);

    //Создание теста
    public void createTest(int countQuestions, String name){
        this.name=name;
        this.countQuestions=countQuestions;
        try{
            File file = new File(name+".txt");
            try {
                if(!file.exists()){
                    file.createNewFile();
                }else{
                    System.out.println("Такой тест уже существует");
                    return;
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("tests.txt",true));
            writer.write(name+"\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Добавление вопросов в тест
    public void addQuestions(){
        int counter=0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(name+".txt",true));
            while (counter<countQuestions){
                System.out.println("Введите "+(counter+1)+" вопрос для добавления в тест");
                question=scanner.nextLine();
                try {
                    File file = new File(question+".txt");
                    if(!file.exists()){
                        System.out.println("Такого вопроса нету");
                        continue;
                    }
                    writer.write(question+"\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                counter++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Вопросы добавлены");
    }

    public void showTests(){
        try{
            File file = new File("tests.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
