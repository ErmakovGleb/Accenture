import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PassingTest {
    Scanner scanner = new Scanner(System.in);

    public String selectTest(){
        System.out.println("Введите название теста для прохождения");
        String name = scanner.nextLine();
        return name;
    }
}
