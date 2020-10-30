import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    String question, userString;
    String author="admin";
    int type;
    Scanner scanner = new Scanner(System.in);

    //Ввод вопроса
    public void setQuestion(){
        System.out.println("Введите вопрос");
        question=scanner.nextLine();
    }

    //Сохранение в файл
    public void typeQuestion(){
        System.out.println("Выберите вид вопроса, введите его номер:\r\n " +
                "1) С единственным правильным вариантом " +
                "2) С множественными правильными вариантами " +
                "3)Открытый вопрос");
        userString = scanner.nextLine();
        if(!userString.equalsIgnoreCase("1")&&!userString.equalsIgnoreCase("2")&&!userString.equalsIgnoreCase("3")){
            System.out.println("Некорректные данные");
            return;
        }
        if (userString.equalsIgnoreCase("1")){
            type=Integer.valueOf(userString);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("questions.txt",true));
                writer.write(question+" "+author+" "+type+"\r\n");
                writer.close();
                answer(3);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (userString.equalsIgnoreCase("2")){
            type=Integer.valueOf(userString);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("questions.txt",true));
                writer.write(question+" "+author+" "+type+"\r\n");
                writer.close();
                answer(4);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (userString.equalsIgnoreCase("3")){
            type=Integer.valueOf(userString);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("questions.txt",true));
                writer.write(question+" "+author+" "+type+"\r\n");
                writer.close();
                answer(1);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Сохранение ответов
    public void answer(int size){
        String[] answers = new String[size];
        if (type==1){
            System.out.println("Введите правильный вариант ответа");
            answers[0]=scanner.nextLine()+" correct";
            for (int i=1;i<size;i++){
                System.out.println("Введите "+(i+1)+" вариант ответа");
                answers[i]=scanner.nextLine()+" incorrect";
            }
        }
        if(type==2){
            for (int i=0;i<2;i++){
                System.out.println("Введите "+(i+1)+" правильный вариант ответа");
                answers[i]=scanner.nextLine()+" correct";
            }
            for (int i=2;i<size;i++){
                System.out.println("Введите "+(i+1)+" вариант ответа");
                answers[i]=scanner.nextLine()+" incorrect";
            }
        }
        if(type==3){
            System.out.println("Введите правильный вариант ответа");
            answers[0]=scanner.nextLine()+" correct";
        }

        File file = new File(question+".txt");

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                for (int i=0; i<size; i++) {
                    out.print(answers[i] + "\r\n");
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    //удаление вопроса
    public void deleteQuestion(String question){
        try{
            int index=-1;
            File file = new File("questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            ArrayList<String> list = new ArrayList<String>();
            while((line = reader.readLine()) != null ){
                if(!line.isEmpty()){
                    list.add(line);
                }
            }
            for(int i=0; i<list.size(); i++) {
                String str=list.get(i);
                //Первая строка неправильно сравнивается
                if(question.equalsIgnoreCase(str.substring(0,str.indexOf(" admin")))){
                    index=i;
                }
            }
            if(index!=-1){
                for (int i=index;i<list.size()-1;i++){
                    list.set(i,list.get(i+1));
                }
                list.remove(list.size()-1);
                list.trimToSize();
                reader.close();
                file.delete();
                file.createNewFile();
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());

                try {
                    for (int i=0; i<list.size(); i++) {
                        out.print(list.get(i) + "\r\n");
                    }
                } finally {
                    out.close();
                }

                file = new File(question+".txt");
                file.delete();
                System.out.println("Вопрос удален");
            }else {
                System.out.println("Вопрос не найден");
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Просмотреть вопросы
    public void show(){
        try{
            File file = new File("questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line.substring(0,line.indexOf(" admin")));
            }
        }catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    //Редактирование
    public void update(){

    }

    //Получение информации о вопросе
    public void getQuestion(String question){
        try{
            File file = new File("questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null){
                if(question.equalsIgnoreCase(line.substring(0,line.indexOf(" admin")))){
                    System.out.println("Вопрос: "+question);
                    System.out.println("Автор: admin");
                    if(Character.getNumericValue(line.charAt(line.length()-1))==1){
                        System.out.println("Вид вопроса: вопрос с одним ответом");
                    }
                    if(Character.getNumericValue(line.charAt(line.length()-1))==2){
                        System.out.println("Вид вопроса: вопрос с множеством ответов");
                    }
                    if(Character.getNumericValue(line.charAt(line.length()-1))==3){
                        System.out.println("Вид вопроса: вопрос с открытым ответом");
                    }
                    break;
                }
            }
            System.out.println("Такого вопроса нету");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
