import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int level=0, userCount, userNumbers=0, div, num, cows=0,bulls=0,index=0,countTry=0;
        String userString;
        String correctString;
        boolean gameOver =false;

        Scanner scanner = new Scanner(System.in);
        Scanner string = new Scanner(System.in);
        System.out.println("Введите сложность игры");
        if(scanner.hasNextInt()){
            level = scanner.nextInt();
            if(level>=3&&level<=5){
                int[] numbers = new int[level];
                numbers[0]=(int)(Math.random()*10);
                correctString=String.valueOf(numbers[0]);

                for(int i=1;i<level;i++){
                    numbers[i]=(int)(Math.random()*10);
                    for(int j=0;j<i;j++){
                        if(numbers[j]==numbers[i]){
                            numbers[i]=(int)(Math.random()*10);
                            j=-1;
                        }
                    }
                    correctString=correctString+String.valueOf(numbers[i]);
                }

                System.out.println("Попробуйте отгадать последовательность");
                while (!gameOver) {
                    if (string.hasNextInt()) {
                        userString = string.nextLine();
                        userCount = userString.length();
                        userNumbers = Integer.parseInt(userString);
                        if (userCount == level) {
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
                                System.out.println("Вы победили! Загаданное число- " + correctString+", количество попыток - "+countTry);
                                gameOver=true;
                            } else {
                                System.out.println("Попробуйте еще раз, количество коров - " + cows + " количество быков - " + bulls);
                            }
                        } else {
                            System.out.println("Нерпавильная длина, попробуйте еще раз");
                        }
                    } else {
                        userString = string.nextLine();
                        if (userString.equalsIgnoreCase("сдаюсь")) {
                            System.out.println("Последовательность была: " + correctString);
                            gameOver=true;
                        }else{
                            System.out.println("Введите последовательность цифр или сдаюсь");
                        }
                    }
                }
            }else{
                System.out.println("Выход из программы");
            }
        }else{
            System.out.println("Выход из программы");
        }

    }
}
