import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    List<String> questions = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);

    //Создание теста
    public String getName(){
        System.out.println("Введите имя теста");
        String name = scanner.nextLine();
        return name;
    }

    public int getCountQuestions(){
        while (true) {
            System.out.println("Введите количество вопросов для теста, не больше 30");
            if (scanner.hasNextInt()) {
                int count = scanner.nextInt();
                if(count>0&&count<=30) {
                    return count;
                }
                System.out.println("Количество вопросов должно быть больше 0 и не больше 30");
            }
            System.out.println("Некорректные данные");
        }
    }
}
