import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    Scanner scanner = new Scanner(System.in);

    //Ввод вопроса
    public String getQuestion(){
        String question;
        System.out.println("Введите вопрос");
        question=scanner.nextLine();
        return question;
    }

    //Ввод типа
    public String getType(){
        String type;
        System.out.println("Выберите вид вопроса, введите его номер:\r\n " +
                "1) С единственным правильным вариантом " +
                "2) С множественными правильными вариантами " +
                "3)Открытый вопрос");
        type = scanner.nextLine();
        while (true){
            if(type.equalsIgnoreCase("1")||type.equalsIgnoreCase("2")||type.equalsIgnoreCase("3")){
                return type;
            }
            System.out.println("Некорректные данные, введите еще раз");
            type=scanner.nextLine();
        }
    }

    public String getAnswerFirstType(int number){
        String answer;
        if(number == 1){
            System.out.println("Введите правильный ответ:");
            answer=scanner.nextLine();
            return answer;
        }
        System.out.println("Введите не правильный ответ:");
        answer=scanner.nextLine();
        return answer;
    }

    public String getAnswerSecondType(int number){
        String answer;
        if(number == 1 || number == 2){
            System.out.println("Введите правильный ответ:");
            answer=scanner.nextLine();
            return answer;
        }
        System.out.println("Введите не правильный ответ:");
        answer=scanner.nextLine();
        return answer;
    }

    public String getAnswerThirdType(){
        String answer;
        System.out.println("Введите правильный ответ:");
        answer=scanner.nextLine();
        return answer;
    }

}