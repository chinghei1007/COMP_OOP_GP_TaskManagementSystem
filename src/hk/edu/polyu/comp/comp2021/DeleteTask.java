package hk.edu.polyu.comp.comp2021;

import java.util.Scanner;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;

//import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteTask {
    public static void delTaskMenu(String fileName){
        String taskName = "";
        Scanner scanner = new Scanner(System.in);
        while (taskName.isEmpty() || !taskName.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
            System.out.print("\n\tEnter task name: ");
            taskName = scanner.nextLine();
            if (taskName.isEmpty()) {
                System.out.println("Error: Task name could not be empty");
                continue;
            }else if (taskName.equals("exit()")){
                System.out.println("Operation cancelled");return;}
            else 
            if (!taskName.matches("^[A-Za-z][0-9A-Za-z]{0,7}")) {
                System.out.println("Error: Task name could not start with digits and the task name should only contains letter and digits");
                taskName = "";
                continue;
            }
        }
        delTask(fileName,taskName);
        
    }
    public static void delTask(String fileName, String string) {
        try {
            List<String> content = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
    
            reader.close();
    
            // Find the duplicate
            String[] stringsplit = string.split("\\|");
            String newtaskName = string;
            int index = -1; // Duplicate index
            for (int i = 0; i < content.size(); i++) {
                String[] splitted = content.get(i).split("\\|");
                if (splitted.length == 1){continue;}
                String oldtaskName = splitted[1];
                if (oldtaskName.equals(newtaskName)) {
                    index = i;
                    break;
                }
            }
    
            if (index != -1) {
                String[] splitted = content.get(index).split("\\|");
                String prerequisite;
                if (splitted.length == 4) {
                    prerequisite = splitted[3];
                } else {
                    prerequisite = splitted[4];
                }
                content.remove(index);
    
                // Remove prerequisite references
                for (int i = 0; i < content.size(); i++) {
                    String[] taskSplit = content.get(i).split("\\|");
                    if (taskSplit.length >= 5) {
                        String taskPrerequisite = taskSplit[4];
                        List<String> prerequisites = new ArrayList<>(Arrays.asList(taskPrerequisite.split(",")));
                        prerequisites.removeIf(pr -> pr.trim().equals(newtaskName));
    
                        taskSplit[4] = prerequisites.isEmpty() ? "," : String.join(",", prerequisites);
                        content.set(i, String.join("|", taskSplit));
                    } else if (taskSplit.length == 4) {
                        continue;
                    }
                }
            }
    
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
            for (String writeline : content) {
                writer.write(writeline);
                writer.newLine();
            }
            writer.close();
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }
    }
    public static void delTaskAndUpdatePrerequisites(String fileName, String oldTaskName, String newTaskName) {
        try {
            List<String> content = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

            reader.close();

            int index = -1; // Task index
            for (int i = 0; i < content.size(); i++) {
                String[] splitted = content.get(i).split("\\|");
                if (splitted.length == 1) {
                    continue;
                }
                String taskName = splitted[1];
                if (taskName.equals(oldTaskName)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                content.remove(index);

                // Update prerequisite references
                for (int i = 0; i < content.size(); i++) {
                    String[] taskSplit = content.get(i).split("\\|");
                    if (taskSplit.length >= 5) {
                        String taskPrerequisite = taskSplit[4];
                        List<String> prerequisites = new ArrayList<>(Arrays.asList(taskPrerequisite.split(",")));
                        for (int j = 0; j < prerequisites.size(); j++) {
                            if (prerequisites.get(j).trim().equals(oldTaskName)) {
                                prerequisites.set(j, newTaskName);
                            }
                            if (prerequisites.get(j).isEmpty()){
                                prerequisites.remove(j);
                                j--;
                            }
                        }
                        //clear empty items
                        taskSplit[4] = prerequisites.isEmpty() ? "," : String.join(",", prerequisites);
                        content.set(i, String.join("|", taskSplit));
                    } else if (taskSplit.length == 4) {
                        continue;
                    }
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
            for (String writeline : content) {
                writer.write(writeline);
                writer.newLine();
            }
            writer.close();
            System.out.println("Task deleted and prerequisites updated");
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }
    }
        
}
    


