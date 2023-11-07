package hk.edu.polyu.comp.comp2021;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicScrollPaneUI.ViewportChangeHandler;

public class ChangeProperty extends Menu {
    public static void changeproperty(String fileName) {
        String[] splitted = new String[0];
        boolean isSimple = true;
        Scanner scanner = new Scanner(System.in);
        String task;
        while (true) {
            changepropertymenu1(fileName);
            task = scanner.nextLine();
            if (task.equals("exit()")) {
                System.out.println("Operation cancelled");
                return;
            } else if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println("Error: invalid task name");
            } else if (!CreateTask.checkExist(fileName, task)) {
                System.out.println("Error: Task doesn't exist, please try again");
            } else if (CreateTask.checkExist(fileName, task)) {
                break;
            }
        }

        try {// check for duplicates
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            
            String line;
            int i = 0;
            reader.close();
            reader = new BufferedReader(new FileReader(fileName));
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                splitted = line.split("\\|");
                String taskName = splitted[1];
                String Task = task;

                // if duplicate found
                if (taskName.equals(Task)&&splitted[0].equals("c")) {
                    isSimple = false;
                    found = true;
                    i++;
                    break;
                } else if (taskName.equals(Task)&&splitted[0].equals("s")){
                    isSimple = true;
                    found = true;
                    i++;
                    break;
                }
            }
            while(true){
        
                System.out.println("\nYour task information is as follows: ");
                printOneTask(task);
                System.out.println("\nHow would you like to edit it?");
                if (isSimple) {changepropertymenu_simple();changesimple(fileName,splitted);return;}
                if (!isSimple) {changepropertymenu_composite();changecomposite(fileName,splitted);return;}
        
                }
        

        } catch (IOException e) {
            System.out.println("File cannot be read");
        }
        
    }

    public static void changesimple(String fileName, String[] splitted){
        Scanner scanner = new Scanner(System.in);
        String name = splitted[1];
        String decription = splitted[2];
        String duration = splitted[3];
        String prerequisite = splitted[4];
        String choice = scanner.nextLine().trim();
        String[] propertyNo = choice.split(",");
        

        for (String number : propertyNo){
            switch (number.trim()){
                case "exit()":
                    System.out.println("Operation cancelled");
                    return;
                case "1":
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    if (!newName.matches("^[A-Za-z][0-9A-Za-z]{0,7}")){
                        System.out.println("Error: Invalid name format. Start with a letter and no longer than 8 characters");
                        continue;
                    }
                    DeleteTask.delTaskAndUpdatePrerequisites(fileName,name,newName);
                    name = newName;
                    break;
                case "2":
                    System.out.print("Enter new description: ");
                    decription = scanner.nextLine();
                    break;
                case "3":
                    System.out.print("Enter new duration: ");
                    duration = scanner.nextLine();
                    break;
                case "4":
                    System.out.print("Enter new prerequisite: ");
                    String newPrerequisite = scanner.nextLine();
                    if (!CreateTask.checkExist(fileName, newPrerequisite)){
                        System.out.println("Task does not exist.");
                        continue;
                    }
                    prerequisite = newPrerequisite;
                    break;
                default:
                    System.out.println("Invalid property number: " + number);
                    return;
            }
        }
        System.out.println("Updated properties for simple task:");
        System.out.print("Name: " + name + " Description: " + decription + " Duration: " + duration + " Prerequisite: " + prerequisite);
        TextFile.appendfile(fileName,"s|" + name + "|" + decription + "|" + duration + "|" + prerequisite);
    }
    public static void changecomposite(String fileName, String[] splitted){
        Scanner scanner = new Scanner(System.in);
        String name = splitted[1];
        String description = splitted[2];
        String groupTask = splitted[3];
        String choice = scanner.nextLine();
        String[] propertyNo = choice.split(",");

        for (String number:propertyNo){
            switch(number.trim()){
                case "exit()":
                    System.out.println("Operation cancelled");
                    return;
                case "1":
                    String newName = scanner.nextLine();
                    if (!newName.matches("^[A-Za-z][0-9A-Za-z]{0,7}")){
                        System.out.println("Error: Invalid name format. Start with a letter and no longer than 8 characters");
                        continue;
                    }
                    name = newName;
                    break;
                case "2":
                    System.out.print("Enter new description: ");
                    description = scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Enter new Group task, separated with comma");
                    String grpTsk = scanner.nextLine();
                    if (!CreateTask.checkExist(fileName,grpTsk)){
                        System.out.println("Task does not exist.");
                        continue;
                    }
                    groupTask = grpTsk;
                    break;
                default:
                    System.out.println("Invalid number: "+number);
                    return;
            }
        }
        System.out.println("Updated Composite task information");
        System.out.println("Name: "+ name + " Description: " + description + " Group Task: " + groupTask);
        overwrite(fileName,"c|" + name + "|" + description + "|" + groupTask);
    }

}
