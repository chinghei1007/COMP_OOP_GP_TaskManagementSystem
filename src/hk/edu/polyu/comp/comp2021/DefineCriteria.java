package hk.edu.polyu.comp.comp2021;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DefineCriteria extends Print {
    private Map<String, Criterion> criteria;

    public DefineCriteria() {
        this.criteria = new HashMap<>();
    }

    public void defineCriteria(String fileName) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("\n\nDefine a new criterion:");
                System.out.println("\t1. Define a criterion");
                System.out.println("\t2. Define a NegatedCriterion");
                System.out.println("\t3. Define a BinaryCriterion");
                System.out.println("\t4. Print all criteria");
                System.out.println("\t5. Searching for tasks based on an existing criterion");
                System.out.println("\t6. Go to the previous menu");
                choice = 0;
                System.out.print("Please enter your choice: ");
                choice = scanner.nextInt();
                if (choice == 1) {getUserInputAndDefineCriterion();}
                else if (choice == 2) {TaskCriteriaComparator.defineNegatedCriterion();}
                else if (choice == 3) {TaskCriteriaComparator.defineBinaryCriterion();}
                else if (choice == 4) {printAllCriteria();}
                else if (choice == 5) {TaskCriteriaComparator.search(fileName);}
                else if (choice == 6) {break;}
                else {
                    System.out.println("Please input the number in the menu");
                }
            } catch (Exception e){
                System.out.println("Please input only numbers.\n\n");
            }
        }
    }

    private void getUserInputAndDefineCriterion() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] parts;

        while (true) {
            System.out.println("Enter criterion name, property, operator, and value (separated by spaces):");
            input = scanner.nextLine();
            parts = input.split(" ");

            if (parts.length == 4) {
                String name = parts[0];
                String property = parts[1];
                String operator = parts[2];
                String value = parts[3];

                if(TaskCriteriaComparator.CheckSameCriteria(name)) {
                    while (true) {
                        try {
                            System.out.println("A criterion with this name already exists. Would you like to:");
                            System.out.println("1. Modify the existing criterion");
                            System.out.println("2. Try again with a different name");
                            System.out.print("Please enter your choice: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            if (choice == 1) {
                                TaskCriteriaComparator.DelCriteral(name);
                                break;
                            }
                            else if (choice == 2) {getUserInputAndDefineCriterion();}
                            else {
                                System.out.println("Please input the number in the menu");
                            }
                        } catch (Exception e){
                            System.out.println("Please input only numbers.\n\n");
                        }
                    }
                }


                if (!property.equals("name") && !property.equals("description") && !property.equals("duration") && !property.equals("prerequisites")) {
                    System.out.println("Invalid property. The property must be either name, description, duration, or prerequisites. Please try again.");
                    continue;
                }

                if ((property.equals("name") || property.equals("description")) && !operator.equals("contains")) {
                    System.out.println("Invalid operator. If the property is name or description, the operator must be contains. Please try again.");
                    continue;
                }

                if (property.equals("duration") && !(operator.equals(">") || operator.equals("<") || operator.equals(">=") || operator.equals("<=") || operator.equals("==") || operator.equals("!="))) {
                    System.out.println("Invalid operator. If the property is duration, the operator can be >, <, >=, <=, ==, or !=. Please try again.");
                    continue;
                }

                if ((property.equals("prerequisites") || property.equals("subtasks")) && !operator.equals("contains")) {
                    System.out.println("Invalid operator. If the property is prerequisites or subtasks, the operator must be contains. Please try again.");
                    continue;
                }

                Criterion criterion = new Criterion(name, property, operator, value);
                // criteria.put(name, criterion); // You need to decide how to handle this line as 'criteria' is non-static
                System.out.println("Criterion defined: " + name + " " + property + " " + operator + " " + value);
                // Save the criterion to a file
                writeFile("criteria.txt", name + "|" + property + "|" + operator + "|" + value);
            } else {
                System.out.println("Invalid input. Please enter exactly four arguments.");
            }


            System.out.println("\n\t1. Define another criterion" +
                    "\n\t2. Return to main menu");
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();

            if (!choice.equals("1")) {
                break;
            }
        }
    }

    public static void printAllCriteria() {
        try (BufferedReader reader = new BufferedReader(new FileReader("criteria.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void writeFile(String fileName, String string){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.newLine();
            writer.append(string);

            writer.close();
            System.out.println("Data added successfully");
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }
    }
    private class Criterion {
        private String name;
        private String property;
        private String operator;
        private String value;

        public Criterion(String name, String property, String operator, String value) {
            this.name = name;
            this.property = property;
            this.operator = operator;
            this.value = value;
        }
        public String getName() {
            return name;
        }

        public String getProperty() {
            return property;
        }

        public String getOperator() {
            return operator;
        }

        public String getValue() {
            return value;
        }


    }
}

