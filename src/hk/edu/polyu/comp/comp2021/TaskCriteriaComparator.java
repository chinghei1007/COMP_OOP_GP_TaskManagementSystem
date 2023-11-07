package hk.edu.polyu.comp.comp2021;

import java.io.*;
import java.util.Scanner;

import static hk.edu.polyu.comp.comp2021.DefineCriteria.writeFile;

public class TaskCriteriaComparator {
    public static void search(String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here are a list of all your criteria");
        DefineCriteria.printAllCriteria();
        System.out.print("\nEnter the criterion name: ");
        String criterionName = scanner.nextLine();
        if (criterionName.equals("IsPrimitive")){
            if(IsPrimitve(fileName)){
                Print.printSimpleTask(fileName);
            }
        }
        else {
            try (BufferedReader criteriaReader = new BufferedReader(new FileReader("Criteria.txt"));
                 BufferedReader taskReader = new BufferedReader(new FileReader(fileName))) {

                String line;
                while ((line = criteriaReader.readLine()) != null) {
                    String[] criterionParts = line.split("\\|");
                    if (criterionParts[0].equals(criterionName)) {
                        String property = criterionParts[1];
                        String operator = criterionParts[2];
                        String value = criterionParts[3];

                        boolean taskFound = false;
                        String taskLine;
                        while ((taskLine = taskReader.readLine()) != null) {
                            String[] taskParts = taskLine.split("\\|");
                            if (taskParts.length < 5) {
                                continue;  // Skip this line if it doesn't have enough parts
                            }
                            switch (property) {
                                case "name":
                                    if (taskParts[1].contains(value) && operator.equals("contains")) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    else if(!taskParts[1].contains(value) && operator.equals("does not contain")) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    break;
                                case "description":
                                    if (taskParts[2].contains(value)) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    else if(!taskParts[1].contains(value) && operator.equals("does not contain")) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    break;
                                case "duration":
                                    double duration = Double.parseDouble(taskParts[3]);
                                    double criterionValue = Double.parseDouble(value);
                                    if ((operator.equals(">") && duration > criterionValue) ||
                                            (operator.equals("<") && duration < criterionValue) ||
                                            (operator.equals(">=") && duration >= criterionValue) ||
                                            (operator.equals("<=") && duration <= criterionValue) ||
                                            (operator.equals("==") && duration == criterionValue) ||
                                            (operator.equals("!=") && duration != criterionValue)) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    break;
                                case "prerequisites":
                                    if (taskParts[4].contains(value)) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    else if(!taskParts[1].contains(value) && operator.equals("does not contain")) {
                                        System.out.println(taskLine.substring(2));
                                        taskFound = true;
                                    }
                                    break;
                            }
                        }

                        if (!taskFound) {
                            System.out.println("No tasks found that match the criterion.");
                        }

                        return;
                    }
                }

                System.out.println("Criterion not found.");
            } catch (IOException e) {
                System.out.println("Error reading file.");
            }
        }
    }

    public static void defineNegatedCriterion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here are a list of all your criteria");
        DefineCriteria.printAllCriteria();
        System.out.print("\nEnter the new criterion name and  : ");
        String NegcriterionName = scanner.nextLine();
        if(CheckSameCriteria(NegcriterionName)){
            System.out.println("Please try again with the different name");
            defineNegatedCriterion();
        }
        System.out.print("\nEnter the criterion name that you want to negated : ");
        String criterionName = scanner.nextLine();
        String line;
        try (BufferedReader criteriaReader = new BufferedReader(new FileReader("Criteria.txt"))) {
            while ((line = criteriaReader.readLine()) != null) {
                String[] criterionParts = line.split("\\|");
                if (criterionParts[0].equals(criterionName)) {
                    String property = criterionParts[1];
                    String operator = criterionParts[2];
                    String value = criterionParts[3];
                    String NewOp;
                    // Negate the operator
                    switch (operator) {
                        case ">":
                            NewOp = "<=";
                            break;
                        case "<":
                            NewOp = ">=";
                            break;
                        case ">=":
                            NewOp = "<";
                            break;
                        case "<=":
                            NewOp = ">";
                            break;
                        case "==":
                            NewOp = "!=";
                            break;
                        case "!=":
                            NewOp = "==";
                            break;
                        case "contains":
                            NewOp = "does not contain";
                            break;
                        case "does not contain":
                            NewOp = "contains";
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + operator);
                    }

                    // Save the new criterion to a file
                    writeFile("criteria.txt", NegcriterionName + "|" + property + "|" + operator + "|" + value);
                    System.out.println("The original Criteria : " + NegcriterionName + " " + property + " " + operator + " " + value);
                    System.out.println("The new NegatedCriteria defined: " + NegcriterionName + " " + property + " " + NewOp + " " + value);
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        System.out.println("Criterion not found.");
    }

    public static void defineBinaryCriterion() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here are a list of all your criteria");
        DefineCriteria.printAllCriteria();
        System.out.print("\nEnter the new BinaryCriterion name : ");
        String BinaryCriterionName = scanner.nextLine();
        System.out.print("\nEnter the First criteria name : ");
        String FirstCriterionName = scanner.nextLine();
        System.out.print("\n1. " + FirstCriterionName + "&&");
        System.out.print("\n2. " + FirstCriterionName + "||");
        String logOp = null;
        while(logOp == null){
            System.out.print("\nPlease enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // Add this line
                if (choice == 1) {
                    logOp = "&&";
                }
                else if (choice == 2) {
                    logOp = "||";
                }
                else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } catch (Exception e){
                System.out.println("Invalid input. Please enter a number.\n\n");
                scanner.next(); // This will consume the invalid token
            }
        }
        System.out.print("\nEnter the Second criteria name : ");
        String SecondCriterionName = scanner.nextLine();
        String line2 = null, line3 = null;
        try (BufferedReader criteriaReader = new BufferedReader(new FileReader("Criteria.txt"))) {
            String line;
            while ((line = criteriaReader.readLine()) != null) {
                String[] criterionParts = line.split("\\|");
                if (criterionParts[0].equals(FirstCriterionName)) {
                    line2 = line;
                }
                if (criterionParts[0].equals(SecondCriterionName)) {
                    line3 = line;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        if (line2 == null || line3 == null) {
            System.out.println("Criterion not found.");
            return;
        }

        // Save the new criterion to a file
        writeFile("Criteria.txt", BinaryCriterionName + "|" + line2 + "|" + logOp + "|" + line3);
        System.out.println("Criterion defined: " + BinaryCriterionName + " " + line2 + " " + logOp + " " + line3);
    }
    public static boolean CheckSameCriteria(String name) {
        try (BufferedReader criteriaReader = new BufferedReader(new FileReader("criteria.txt"))) {
            String line;
            while ((line = criteriaReader.readLine()) != null) {
                String[] criterionParts = line.split("\\|");
                if (criterionParts[0].equals(name)) {
                    // Criterion found
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        // Criterion not found
        return false;
    }

    public static void DelCriteral(String name) {
        // Create a temporary file
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader("criteria.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            String line;
            String prevLine = null;
            while ((line = reader.readLine()) != null) {
                // Only write lines that do not start with the given name
                if (!line.startsWith(name + "|")) {
                    // Write the previous line if it's not null
                    if (prevLine != null) {
                        writer.println(prevLine);
                    }
                    prevLine = line;
                }
            }
            // Write the last line without a newline character
            if (prevLine != null) {
                writer.print(prevLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing file.");
        }

        // Delete the old file and rename the temporary file
        File oldFile = new File("criteria.txt");
        if (oldFile.delete()) {
            if (!tempFile.renameTo(oldFile)) {
                System.out.println("Error renaming file.");
            }
        } else {
            System.out.println("Error deleting file.");
        }
    }
    public static boolean IsPrimitve(String fileName) {

        try (BufferedReader taskReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = taskReader.readLine()) != null) {
                String[] taskReaderParts = line.split("\\|");
                if (taskReaderParts[0].equals("s")) {
                    // Criterion found
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return false;
    }
}