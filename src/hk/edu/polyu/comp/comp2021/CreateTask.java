package hk.edu.polyu.comp.comp2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CreateTask extends Menu {

    public static void createTask(String fileName) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                createtaskmenu(); // get the menu
                choice = 0; // each time it loops
                choice = scanner.nextInt();
                if (choice == 1) {
                    createSimpleTask(fileName);
                } else if (choice == 2) {
                    createCompositeTask(fileName);
                } else if (choice == 3) {
                    break;
                } // go to the previous menu
                else {
                    System.out.println("Error: Please only input numbers in the menu");
                }
            } catch (Exception e) {
                System.out.println("Error, Please input numbers");

            }
        }
    }

    public static void createSimpleTask(String fileName) {
        System.out.println("\n\nCreating Simple Task");
        Scanner scanner = new Scanner(System.in);
        String task = "";
        String description = "";
        String duration = "";
        // name
        while (task.isEmpty() || !task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
            System.out.print("\n\tEnter task name: ");
            task = scanner.nextLine();
            if (task.isEmpty()) {
                System.out.println("Error: Task name could not be empty");
                continue;
            } else if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println(
                        "Error: Task name could not start with digits and the task name should only contains letter and digits");
                task = "";
                continue;
            }
        }
        // description
        System.out.print("\n\tPlease enter the description of the task: ");
        description = scanner.nextLine();

        // duration
        System.out.print("\n\tPlease enter the duration of the task: ");
        while (duration.isEmpty()) {
            float tempduration = 0;
            try {
                tempduration = scanner.nextFloat();
                if (tempduration < 0) {
                    System.out.println("Error: Duration could not be negative");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Please input only decimals");
                duration = "";
                scanner.next();
            }
            duration = String.valueOf(tempduration);
            scanner.nextLine();
        }
        // Prerequisite
        String prerequisite = ",";
        if (TextFile.isEmpty("Task.txt")) {
        } else {
            while(true){
            String choice = "";
            System.out.println("\tWould you like to add any prerequisite tasks?(y/n)");
            choice = scanner.nextLine();

            if (choice.toLowerCase().equals("y")) {
                
                    System.out.println("Here are a list of all your tasks");
                    printAllTasks(fileName);
                    System.out.println("If you have more than one prerequisite, please seperate them with comma");
                    prerequisite = scanner.nextLine();
                    String[] checkprerequisite = prerequisite.split(",");
                    for (String in : checkprerequisite) {
                        System.out.println(in);
                    }
                    if (checkExist(fileName,checkprerequisite)) {
                        break;
                    } else {
                        System.out.println("There is/are tasks that don't exist, please try again");
                    }
                }
                else if (choice.toLowerCase().equals("n")){
                    break;
                }
            else if (!choice.toLowerCase().equals("y") && !choice.toLowerCase().equals("n")){
                System.out.println("Invalid input, please try again");
                continue;

            }
        }}
        
        while (true) {
            String TorF;
            System.out.println("\nYour task information is as follows");
            System.out.println("Task name: " + task);
            System.out.println("Description: " + description);
            System.out.println("Duration: " + duration + " hrs");
            if (prerequisite.equals(",")) {
                System.out.println("Prerequitsite: " + "none");
            } else {
                System.out.println(prerequisite);
            }
            System.out.print("\nIs this correct? (Y/N): ");
            TorF = scanner.nextLine();

            if (TorF.toLowerCase().equals("y")) {
                String writestring = "s" + "|" + task + "|" + description + "|" + duration + "|" + prerequisite;
                writeFile("Task.txt", writestring);// write file
                break;
            } else if (TorF.toLowerCase().equals("n")) {
                createSimpleTask(fileName);
            } else {
                System.out.println("Please enter either Y or N");
            }
        }

    }

    public static boolean checkExist(String fileName, String[] list) {
        File file = new File(fileName);

        try {// check for duplicates
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // check if the txt is empty
            if (file.length() == 0) {
                reader.close();
                return false;
            }
            int i = 0;
            while (i < list.length) {
                reader.close();
                reader = new BufferedReader(new FileReader(file));
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] splitted = line.split("\\|");
                    String taskName = splitted[1];
                    String Task = list[i].trim();

                    // if duplicate found
                    if (taskName.equals(Task)) {
                        found = true;
                        i++;
                        break;
                    } else {
                    }
                }
                if (found == false) {
                    return false;
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("File cannot be read");
        }
        return true;

    }

    public static boolean checkExist(String fileName, String task) {
        File file = new File(fileName);

        try {// check for duplicates
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // check if the txt is empty
            if (file.length() == 0) {
                reader.close();
                return false;
            }
            int i = 0;

            reader.close();
            reader = new BufferedReader(new FileReader(file));
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split("\\|");
                if (splitted.length == 1){continue;}

                String taskName = splitted[1];
                String Task = task;

                // if duplicate found
                if (taskName.equals(Task)) {
                    found = true;
                    i++;
                    break;
                } else {
                }
            }
            if (found == false) {
                return false;
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("File cannot be read");
        }
        return true;

    }

    public static void createCompositeTask(String fileName) {
        System.out.println("\n\nCreating Composite Task");
        Scanner scanner = new Scanner(System.in);
        String task = "";
        String description = "";
        // name
        while (task.isEmpty() || !task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
            System.out.print("\n\tEnter task name: ");
            task = scanner.nextLine();
            if (task.isEmpty()) {
                System.out.println("Error: Task name could not be empty");
                continue;
            } else if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println(
                        "Error: Task name could not start with digits and the task name should only contains letter and digits");
                task = "";
                continue;
            }
        }
        // description
        System.out.print("\n\tPlease enter the description of the task: ");
        description = scanner.nextLine();

        String compotask = "";
        if (TextFile.isEmpty(fileName)) {
        } else {
            String choice = "";
            System.out.println("Would you like to add any prerequisite tasks?");
            while (true) {
                System.out.println("Here are a list of all your tasks");
                printAllTasks(fileName);
                System.out.println("If you have more than one task, please separate them with comma");
                compotask = scanner.nextLine();
                String[] checkcompo = compotask.split(",");
                for (String in : checkcompo) {
                    System.out.println(in);
                }
                if (checkExist(fileName,checkcompo)) {
                    break;
                } else {
                    System.out.println(
                            "Error: Either you did not type anything or there is/are tasks that don't exist, please try again");
                }
            }
        }
        while (true) {
            String TorF;
            System.out.println("\nYour task information is as follows");
            System.out.println("Task name: " + task);
            System.out.println("Description: " + description);
            if (compotask.equals("")) {
                System.out.println("Task Group: " + "none");
            } else {
                System.out.println("Task Group: " + compotask);
            }
            System.out.print("\nIs this correct? (Y/N): ");
            TorF = scanner.nextLine();

            if (TorF.toLowerCase().equals("y")) {
                String writestring = "c" + "|" + task + "|" + description + "|" + compotask;
                appendfile(fileName,writestring); // write file
                break;
            } else if (TorF.toLowerCase().equals("n")) {
                createSimpleTask(fileName);
            } else {
                System.out.println("Please enter either Y or N");
            }
        }

    }
}
