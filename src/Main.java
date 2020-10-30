import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userString="";
        String login, password, name;
        String role;
        String delQuestion;
        String nameTest;
        int countQuestions;
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
                System.out.println("Введите логин");
                login=scanner.nextLine();
                System.out.println("Введите пароль");
                password=scanner.nextLine();
                authorized=user.authorized(login,password);
            }
            if(userString.equalsIgnoreCase("Нет")){
                System.out.println("Регистрация");
                System.out.println("Введите логин");
                login=scanner.nextLine();
                System.out.println("Введите пароль");
                password=scanner.nextLine();
                System.out.println("Введите имя");
                name=scanner.nextLine();
                String data = login+" "+password+" "+name+" user";
                user.register(login,data);
            }
        }

        //После авторизации
        role=user.getRole();
        while (true){
            //admin
            if(role.equalsIgnoreCase("admin")){
                System.out.println("Для того чтобы прейти к базе вопросов введите Вопросы, для работы с тестами введите Тесты, для выхода введите Выход");
                userString=scanner.nextLine();
                if(!userString.equalsIgnoreCase("Вопросы") && !userString.equalsIgnoreCase("Тесты") && !userString.equalsIgnoreCase("Выход")){
                    System.out.println("Некорректные данные");
                    continue;
                }
                if(userString.equalsIgnoreCase("Вопросы")){
                    System.out.println("Введите действие: добавить, удалить, редактировать, просмотреть");
                    userString=scanner.nextLine();
                    Question question = new Question();
                    if(userString.equalsIgnoreCase("добавить")){
                        question.setQuestion();
                        question.typeQuestion();
                    }
                    else if(userString.equalsIgnoreCase("удалить")){
                        System.out.println("Введите вопрос для удаления");
                        delQuestion=scanner.nextLine();
                        question.deleteQuestion(delQuestion);
                    }else if(userString.equalsIgnoreCase("редактировать")){

                    }else if(userString.equalsIgnoreCase("просмотреть")){
                        question.show();
                    }else{
                        System.out.println("Неккоректные данные");
                    }
                }
                if(userString.equalsIgnoreCase("Тесты")){
                    System.out.println("Создание теста");
                    System.out.println("Введите название теста");
                    nameTest=scanner.nextLine();
                    System.out.println("Введите количество вопросов для теста");
                    if(!scanner.hasNextInt()){
                        System.out.println("Некорректные данные");
                        continue;
                    }
                    countQuestions=scanner.nextInt();
                    Test test = new Test();
                    test.createTest(countQuestions,nameTest);
                    test.addQuestions();
                }
            }
            //user
            if(role.equalsIgnoreCase("user")){
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
                        Test test = new Test();
                        test.showTests();
                    }
                    if(userString.equalsIgnoreCase("пройти")){
                        System.out.println("Введите название теста для прохождения");
                        nameTest=scanner.nextLine();
                        PassingTest test = new PassingTest();
                        test.selectTest(nameTest);
                        test.passing();
                    }
                }
                if(userString.equalsIgnoreCase("Инфо")){
                    System.out.println("Ввдеите название вопроса");
                    String question = scanner.nextLine();
                    Question quest = new Question();
                    quest.getQuestion(question);
                }
            }

            if(userString.equalsIgnoreCase("Выход")){
                System.out.println("Выход из программы");
                return;
            }
        }

    }
}
