import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userString="";
        boolean authorized=false;

        User user = new User();

        //Регистрация и авторизация
        while (!authorized){
            System.out.println("Вы зарегестрированы? Введите Да или Нет. Для выхода из программы напишите Выход");
            userString=scanner.nextLine();

            if(userString.equalsIgnoreCase("Выход")){
                System.out.println("Выход из программы");
                return;
            }
            if(!userString.equalsIgnoreCase("Да") && !userString.equalsIgnoreCase("Нет")){
                System.out.println("Некорректные данные");
            }
            if(userString.equalsIgnoreCase("Да")){
                System.out.println("Авторизация");
                authorized=user.authorized();
            }
            if(userString.equalsIgnoreCase("Нет")){
                System.out.println("Регистрация");
                user.register();
            }
        }

        //После авторизации
        while (true){
            //admin
            if(user.getRole().equalsIgnoreCase("admin")){
                System.out.println("Для того чтобы прейти к базе вопросов введите Вопросы, для работы с тестами введите Тесты, для выхода введите Выход");
                userString=scanner.nextLine();
                if(!userString.equalsIgnoreCase("Вопросы") && !userString.equalsIgnoreCase("Тесты") && !userString.equalsIgnoreCase("Выход")){
                    System.out.println("Некорректные данные");
                    continue;
                }
                if(userString.equalsIgnoreCase("Вопросы")){
                    System.out.println("Введите действие: добавить, удалить, редактировать, просмотреть");
                    userString=scanner.nextLine();
                    QuestionServiceImpl question = QuestionServiceImpl.getInstance();
                    if(userString.equalsIgnoreCase("добавить")){
                        question.addQuestion();
                    }
                    else if(userString.equalsIgnoreCase("удалить")){
                        question.deleteQuestion();
                    }else if(userString.equalsIgnoreCase("редактировать")){

                    }else if(userString.equalsIgnoreCase("просмотреть")){
                        question.showQuestions();
                    }else{
                        System.out.println("Неккоректные данные");
                    }
                }
                if(userString.equalsIgnoreCase("Тесты")){
                    System.out.println("Создание теста");
                    QuestionRepositoryImpl test = QuestionRepositoryImpl.getInstance();
                    test.createTest();
                }
            }
            //user
            if(user.getRole().equalsIgnoreCase("user")){
                System.out.println("Для выбора теста напишите Тесты, для просмотра информации о вопросе напишите Инфо, для выхода из программы напишите Выход");
                userString=scanner.nextLine();
                if(!userString.equalsIgnoreCase("Тесты") && !userString.equalsIgnoreCase("Выход") && !userString.equalsIgnoreCase("Инфо")){
                    System.out.println("Некорректные данные");
                    continue;
                }
                if (userString.equalsIgnoreCase("Тесты")){
                    System.out.println("Выберите действие с тестами: пройти, просмотреть");
                    userString=scanner.nextLine();
                    if(!userString.equalsIgnoreCase("пройти") && !userString.equalsIgnoreCase("просмотреть")){
                        System.out.println("Некорректные данные");
                        continue;
                    }
                    if(userString.equalsIgnoreCase("просмотреть")){
                        QuestionRepositoryImpl test = QuestionRepositoryImpl.getInstance();
                        test.showTests();
                    }
                    if(userString.equalsIgnoreCase("пройти")){
                        QuestionRepositoryImpl test = QuestionRepositoryImpl.getInstance();
                        test.passingTest();
                    }
                }
                if(userString.equalsIgnoreCase("Инфо")){
                    QuestionServiceImpl question = QuestionServiceImpl.getInstance();
                    question.showInfo();
                }
            }

            if(userString.equalsIgnoreCase("Выход")){
                System.out.println("Выход из программы");
                return;
            }
        }

    }
}
