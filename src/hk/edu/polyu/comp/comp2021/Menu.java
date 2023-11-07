package hk.edu.polyu.comp.comp2021;

public class Menu extends TextFile {
    public static void mainmenu() {
        System.out.println("\n\t1. Create Simple/Composite Tasks" +
                "\n\t2. Delete Tasks" +
                "\n\t3. Change property of tasks" +
                "\n\t4. Printing information of tasks" +
                "\n\t5. Define criteria of tasks" +
                "\n\t6. Shut off the system");
    }

    public static void createtaskmenu() {
        System.out.println("\n\n Which kind of task you want to create?");
        System.out.println("\t1. Simple Tasks");
        System.out.println("\t2. Composite Tasks");
        System.out.println("\t3. Return to the previous menu");
        System.out.println("Please enter your choice");
    }

    public static void changepropertymenu1(String fileName) {
        System.out.println("Here are all the tasks you have created");
        printAllTasks(fileName);
        System.out.println("Please select the task you wish to change property of");
    }

    public static void changepropertymenu_simple() {
        System.out.println("Please enter the property you wish to change (Please only input numbers, separated by commas)");
        System.out.println("\t1. Name");
        System.out.println("\t2. Description");
        System.out.println("\t3. Duration");
        System.out.println("\t4. Prerequisite");
        System.out.println("\t5. Criteria");
    }

    public static void changepropertymenu_composite() {
        System.out.println("Please select the property you wish to change");
        System.out.println("\t1. Name");
        System.out.println("\t2. Description");
        System.out.println("\t3. Group Task");
        System.out.println("\t5. Criteria");
    }

    public static void printmenu() {
        System.out.println("\n\n Which would you like to print?");
        System.out.println("\t1. All Tasks");
        System.out.println("\t2. All Simple Tasks");
        System.out.println("\t3. All Composite Tasks");
        System.out.println("\t4. Information of one task");
        System.out.println("\t5. Return to the previous menu");
        System.out.println("Please enter your choice");
    }
}
