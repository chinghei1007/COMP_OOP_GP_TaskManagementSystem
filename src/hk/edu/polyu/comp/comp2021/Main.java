package hk.edu.polyu.comp.comp2021;

import java.util.Scanner;

public class Main extends Menu {
    public static void main(String[] args) {
        String fileName = "Task.txt";
        int choice;
        DefineCriteria defineCriteria = new DefineCriteria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Task Management System");
            mainmenu();
            try {
                System.out.println("Please enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Error: Please input only numbers on the menu;");
                }

            } catch (Exception e) {
                System.out.println("Error: Please input only numbers");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1:
                    CreateTask.createTask(fileName);
                    break;
                case 2:
                    DeleteTask.delTaskMenu(fileName);
                    break;
                case 3:
                    ChangeProperty.changeproperty(fileName);
                    break;
                case 4:
                    Print.print(fileName);
                    break;
                case 5:
                    defineCriteria.defineCriteria(fileName);
                    break;
                case 6:
                    System.out.println("System will now terminate");
                    return;

            }
        }
    }
}
