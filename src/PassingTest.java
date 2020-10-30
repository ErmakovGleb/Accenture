import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PassingTest {

    String name;
    int countQuestions=0;
    int correctQuestions=0;
    String answer;
    Scanner scanner = new Scanner(System.in);

    //Выбор теста
    public void selectTest(String name){
        this.name=name;
        File file = new File(name+".txt");
        if(!file.exists()){
            System.out.println("Такого теста нету");
            return;
        }
    }

    //Прохождение теста
    public void passing(){
        try {
            File file = new File(name+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null){
                int type=typeQuestion(line);
                if (type==3){
                    openQuestion(line);
                }
                if(type==2){
                    multipleAnswerQuestion(line);
                }
                if(type==1){
                    questionWithOneAnswer(line);
                }
            }
            result();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Тип вопроса
    public int typeQuestion(String question){
        try{
            File file = new File("questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while((line = reader.readLine()) != null ){
                if(question.equalsIgnoreCase(line.substring(0,line.indexOf(" admin")))){
                    int typeQuestion=Character.getNumericValue(line.charAt(line.length()-1));
                    return typeQuestion;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //Вывод и проверка открытых вопросов
    public void openQuestion(String nameQuestion){
        try {
            File file = new File(nameQuestion+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line=reader.readLine();
            String[] lines = line.split(" ");
            System.out.println(nameQuestion);
            System.out.println("Введите свой ответ:");
            answer=scanner.nextLine();
            if(answer.equalsIgnoreCase(lines[0])){
                correctQuestions++;
            }
            countQuestions++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Вывод и проверка вопросов с одним правильным вариантом
    public void questionWithOneAnswer(String nameQuestion){
        try {
            File file = new File(nameQuestion+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            int numberCorrectAnswer=0,counter=1;
            System.out.println(nameQuestion);
            while((line = reader.readLine()) != null ){
                String[] lines = line.split(" ");
                System.out.println(lines[0]);
                if(lines[1].equalsIgnoreCase("correct")){
                    numberCorrectAnswer=counter;
                }
                counter++;
            }
            System.out.println("Введите номер правильного ответа:");
            answer=scanner.nextLine();
            if(answer.equalsIgnoreCase(Integer.toString(numberCorrectAnswer))){
                correctQuestions++;
            }
            countQuestions++;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Вывод и проверка вопрсов с множеством ответов
    public void multipleAnswerQuestion(String nameQuestion){
        try{
            File file = new File(nameQuestion+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            String correctString="";
            int counter=1;
            System.out.println(nameQuestion);
            while((line = reader.readLine()) != null ){
                String[] lines = line.split(" ");
                System.out.println(lines[0]);
                if(lines[1].equalsIgnoreCase("correct")){
                    correctString=correctString+Integer.toString(counter);
                }
                counter++;
            }
            System.out.println("Введите номера ответов подряд без пробелов:");
            answer=scanner.nextLine();
            if(answer.equalsIgnoreCase(correctString)){
                correctQuestions++;
            }
            countQuestions++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Подсчет правильных ответов
    public void result(){
        double percent, correct, count;
        correct=(float) correctQuestions;
        count=(float)countQuestions;
        percent=correct/count*100;
        String str=String.format("Процент правильных отвветов: %f",percent);
        System.out.println("Колиество правильных ответов: "+correctQuestions+"\r\n"+str);
    }

}
