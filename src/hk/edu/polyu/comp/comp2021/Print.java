package hk.edu.polyu.comp.comp2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;

public class Print {
    public static void print(String fileName) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String choice2;
        while (true) {
            try {
                Menu.printmenu();
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Error: Please input numbers in range");
                    continue;
                }
                switch (choice) {
                    case 1: // all task
                        printAllTasks(fileName);
                        continue;
                    case 2: // simeple task
                        printSimpleTask(fileName);
                        continue;
                    case 3: // composite task
                        printCompositeTask(fileName);
                        continue;
                    case 4:// one task
                            try {
                                printTaskNameOnly(fileName);
                                printOneTask(fileName);
                                continue;
                            } catch (Exception e) {
                                System.out.println("Error: Please enter only task name");
                            }
                    case 5:
                        printMinimumDuration(fileName);
                        continue;
                    case 6:
                        return;

                }
            } catch (Exception e) {
                System.out.println("Please type only numbers");
            }
        }

    }

    public static void printCompositeTask(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content = "";
            System.out.println("\nComposite Task");
            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1){continue;}
                if (data[0].equals("s")) {
                    continue;
                } else if (data.length == 1) {continue;} // skip all the composite tasks
                String[] criteria = new String[data.length - 4];
                String task = data[1];
                String description = data[2];
                String GroupTask = data[3];
                for (int i = 4; i < data.length; i++) {
                    criteria[i - 4] = data[i];
                }
                // print
                System.out.print("\nTask Name: " + task + " ");
                System.out.print("Description: " + description + " ");
                if (GroupTask.equals("")) {
                    System.out.print("Prerequisites: None" + " ");
                } else {
                    System.out.print("Prerequisites: " + GroupTask + " ");
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("File could not be read");
        }

    }

    public static void printSimpleTask(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content;
            System.out.println("Simple Task");
            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1){continue;}
                if (data[0].equals("c")) {
                    continue;
                } else if (data.length == 1) {continue;} // skip all the composite tasks
                String task = data[1];
                String description = data[2];
                float duration = Float.parseFloat(data[3]);
                String prerequisite = data[4];
                // print
                System.out.println("\nTask Name: " + task);
                System.out.println("Description: " + description);
                System.out.println("Duration: " + duration);
                if (prerequisite.equals(",")) {
                    System.out.println("Prerequisites: None");
                } else {
                    System.out.println("Prerequisites: " + prerequisite);
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be read");
        }
    }

    public static void printSimpleTaskNameOnly(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content;
            System.out.println("Simple Task");
            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1){continue;}
                String tasktype = data[0];
                if (tasktype.equals("c")) {
                    continue;
                } else if (data.length == 1) {continue;}
                String task = data[1];
                if (tasktype.equals("s")) {
                    tasktype = "Simple ";
                }
                System.out.println(tasktype + "Task: " + task);
            }
        } catch (IOException e) {
            System.out.println("Error: File could not be read");
        }
    }

    public static void printCompositeTaskNameOnly(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content;
            System.out.println("Composite Task");
            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1){continue;}
                String tasktype = data[0];
                if (tasktype.equals("s")) {
                    continue;
                }  else if (data.length == 1) {continue;}
                String task = data[1];
                if (tasktype.equals("c")) {
                    tasktype = "Composite ";
                }
                System.out.println(tasktype + "Task: " + task);
            }
        } catch (IOException e) {
            System.out.println("Error: File could not be read");
        }
    }

    public static void printTaskNameOnly(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content;
            System.out.println("Simple Task");
            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1) {continue;}
                String tasktype = data[0];
                String task = data[1];
                if (tasktype.equals("s")) {
                    tasktype = "Simple ";
                }  else if (data.length == 1) {continue;}
                else {
                    tasktype = "Composite ";
                }
                System.out.println(tasktype + "Task: " + task);
            }
        } catch (IOException e) {
            System.out.println("Error: File could not be read");
        }
    }

    public static void printAllTasks(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String content;
            System.out.println("Simple Task");

            while ((content = reader.readLine()) != null) {
                String[] data = content.split("\\|");
                if (data.length == 1){continue;}
                if (data[0].equals("c")) {
                    continue;
                } else if (data.length == 1) {
                    continue;
                }
                // if (criticallength < 0) {continue;} //skip all the composite tasks //for soem
                // reason it doesn't work in intellij
                String task = data[1];
                String description = data[2];
                float duration = Float.parseFloat(data[3]);
                String prerequisite = data[4];

                // print
                System.out.print("\nTask Name: " + task + " ");
                System.out.print("Description: " + description + " ");
                System.out.print("Duration: " + duration + " ");
                if (prerequisite.equals(",")) {
                    System.out.print("Prerequisites: None" + " ");
                } else {
                    System.out.print("Prerequisites: " + prerequisite + " ");
                
                }
            }

            reader.close();
            printAllTasks2(fileName);

        }catch (IOException e) {
            System.out.println("File could not be read");
        }
    }
    public static void printAllTasks2(String fileName){
        try{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String content = "";
        System.out.print("\nComposite Task");
        while ((content = reader.readLine()) != null) {
            String[] data = content.split("\\|");
            if (data.length == 1){continue;}
            if (data[0].equals("s")) {
                continue;
            } // skip all the composite tasks
            String task = data[1];
            String description = data[2];
            String GroupTask = data[3];
            // print
            System.out.print("\nTask Name: " + task + " ");
            System.out.print("Description: " + description + " ");
            if (GroupTask.equals("")) {
                System.out.print("Prerequisites: None" + " ");
            } else {
                System.out.print("Prerequisites: " + GroupTask + " ");
            }
        }
        reader.close();

    } catch (IOException e) {
        System.out.println("File could not be read");
    }


}
    public static void printOneTask(String fileName,String task) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println("Invalid task name");
                printOneTask(fileName,task);
            }
            if (TextFile.isEmpty(fileName)) {
                reader.close();
                return;
            }
            while ((line = reader.readLine()) != null) {                
                String[] data = line.split("\\|");
                if (data.length == 1) {continue;}
                String txttaskName = data[1];
                String tasktype = data[0];
                String writetask = task;

                // if duplicate found
                if (txttaskName.equals(writetask) && tasktype.equals("s")) {
                    System.out.println("Task found");
                    reader.close();
                    String taskName = data[1];
                    String description = data[2];
                    float duration = Float.parseFloat(data[3]);
                    String prerequisite = data[4];

                    // print
                    System.out.println("Name: " + taskName);
                    System.out.println("Description: " + description);
                    System.out.print("Duration: " + duration + " ");
                    if (prerequisite.equals(",")) {
                        System.out.println("Prerequisite: none");
                    } else {
                        System.out.println("Prerequisite: " + prerequisite);
                    }
                    return;

                } // end of duplicate checking
                else if (txttaskName.equals(writetask) && tasktype.equals("c")) {
                    System.out.println("Task found");
                    reader.close();
                    String taskName = data[1];
                    String description = data[2];
                    String GroupTask = data[3];

                    // print
                    System.out.println("Name: " + taskName);
                    System.out.println("Description: " + description);
                    System.out.println("Group Task: " + GroupTask);
                    return;

                } // end of duplicate checking




            }
            System.out.println("Task not found");

        } catch (IOException e) {
            System.out.println("Error: File cannot be read");
        }
    }
    public static void printMinimumDuration(String fileName){
        Scanner scanner = new Scanner(System.in);
        String task;
        File file = new File(fileName);
        System.out.println("Please input the task you wish to find");
        task = scanner.nextLine();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")){
                System.out.println("Invalid input, please try again");
                printMinimumDuration(fileName);
                return;
            }if (TextFile.isEmpty(fileName)) {
                reader.close();
                System.out.println("Error: This is an empty file");
                return;
            }else if (task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")){
                while ((line = reader.readLine()) != null){

                }
            }
        }catch(IOException e){}
    }
    public static void printOneTask(String fileName) {
        Scanner scanner = new Scanner(System.in);
        String task;
        File file = new File(fileName);
        System.out.println("Please input the task you wish to find");
        task = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            if (!task.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println("Invalid task name");
                printOneTask(fileName);
                return;
            }
            if (TextFile.isEmpty(fileName)) {
                System.out.println("Error: This is an empty file");
                reader.close();
                return;
            }
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 1) {continue;}
                String txttaskName = data[1];
                String tasktype = data[0];
                String writetask = task;

                // if duplicate found
                if (txttaskName.equals(writetask) && tasktype.equals("s")) {
                    System.out.println("Task found");
                    reader.close();
                    String taskName = data[1];
                    String description = data[2];
                    float duration = Float.parseFloat(data[3]);
                    String prerequisite = data[4];

                    // print
                    System.out.println("Name: " + taskName);
                    System.out.println("Description: " + description);
                    System.out.print("Duration: " + duration + " ");
                    if (prerequisite.equals(",")) {
                        System.out.println("Prerequisite: none");
                    } else {
                        System.out.println("Prerequisite: " + prerequisite);
                    }
                    return;

                } // end of duplicate checking
                else if (txttaskName.equals(writetask) && tasktype.equals("c")) {
                    System.out.println("Task found");
                    reader.close();
                    String taskName = data[1];
                    String description = data[2];
                    String GroupTask = data[3];

                    // print
                    System.out.println("Name: " + taskName);
                    System.out.println("Description: " + description);
                    System.out.println("Group Task: " + GroupTask);
                    return;

                } // end of duplicate checking

            }
            System.out.println("Task not found");

        } catch (IOException e) {
            System.out.println("Error: File cannot be read");
        }
    }

    /*public static void printOneTask() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        File file = new File(fileName);
        System.out.println("Please input the task you wish to find");
        choice = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            if (!choice.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println("Invalid task name");
                scanner.close();
                printOneTask();
            }
            if (TextFile.isEmpty(fileName)) {
                reader.close();
                return;
            }
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 1) {continue;}
                String txttaskName = data[1];
                String writetask = choice;

                // if duplicate found
                if (data[0].equals("c")) {
                    System.out.println("Composite task itself do not have duration");
                    return;
                }
                else if (txttaskName.equals(writetask)) {
                    System.out.println("Task found");
                    reader.close();
                    float duration = Float.parseFloat(data[3]);

                    // print
                    System.out.print("Duration: " + duration + " ");
                    System.out.println("\nPlease input any key to continue...");
                    scanner.nextLine();
                    return;

                } // end of duplicate checking
                else {
                    System.out.println("Task not found");

                }
            }
            ;

        } catch (IOException e) {
            System.out.println("Error: File cannot be read");
        }
    }*/
}
