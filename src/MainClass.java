import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int level=0, userCount,countTry=0, userNumbers=0;
        String userString;
        String correctString="";
        boolean gameOver =false;

        Scanner scanner = new Scanner(System.in);
        Scanner string = new Scanner(System.in);
        System.out.println("Введите сложность игры");

        if(!scanner.hasNextInt()){
            System.out.println("Выход из программы");
            return;
        }

        level = scanner.nextInt();

        if(level<3||level>5){
            System.out.println("Выход из программы");
            return;
        }

        Sequence array = new Sequence(level);
        array.Generation();
        correctString=array.GetString();
        System.out.println("Попробуйте отгадать последовательность");

        while (!gameOver) {
            if (!string.hasNextInt()) {
                userString = string.nextLine();
                if (userString.equalsIgnoreCase("сдаюсь")) {
                    System.out.println("Последовательность была: " + correctString);
                    gameOver=true;
                }else{
                    System.out.println("Введите последовательность цифр или сдаюсь");
                }
                continue;
            }
            userString = string.nextLine();
            userCount = userString.length();
            userNumbers = Integer.parseInt(userString);
            if (userCount != level) {
                System.out.println("Нерпавильная длина, попробуйте еще раз");
                continue;
            }
            countTry=Check(level,countTry,userNumbers,array.GetArray(),correctString);
        }
    }

    public static int Check(int level,int countTry,int userNumbers, int[] numbers, String correctString){
        int div, num, cows=0,bulls=0,index=0;
        bulls = 0;
        cows = 0;
        countTry++;
        for (int i = 0; i < level; i++) {
            div = level-1;
            num = userNumbers;
            index = 0;
            while (div != -1) {
                if (numbers[i] == (int)(num / Math.pow(10, div))) {
                    if (i == index) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
                num = num - (int)((int)(num / Math.pow(10, div)) * Math.pow(10, div));
                div--;
                index++;
            }
        }
        if (bulls == level) {
            System.out.println("Вы победили! Загаданное число - " + correctString+", количество попыток - "+countTry);
            System.exit(0);
        } else {
            System.out.println("Попробуйте еще раз, количество коров - " + cows + " количество быков - " + bulls);
        }
        return countTry;
    }
}
