package hk.edu.polyu.comp.comp2021;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFile extends Print{
    public static void writeFile(String fileName, String string){
        Scanner scanner = new Scanner(System.in);
        File file = new File(fileName);

        try {
            String choice = "";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            if (isEmpty(fileName)){
                appendfile(fileName,string);
                reader.close();
                return;
            }
            while ((line = reader.readLine()) != null){
                String[] splitted = line.split("\\|");
                if (splitted.length == 1){continue;}
                String taskName = splitted[1];
                String writetask = string.split("\\|")[1];

                //if duplicate found
                if (taskName.equals(writetask)) {
                    System.out.println("Duplicate task name found");
                    reader.close();
                    while(choice.isEmpty()){
                        System.out.println("Do you want to overwrite the existing task? (Y/N)");
                        choice = scanner.nextLine();
                        if (choice.toLowerCase().equals("y")){
                            overwrite(fileName,string);
                            return;
                        }else if (choice.toLowerCase().equals("n")){
                            System.out.println("This will not change anything");
                            return;
                        }else{
                            System.out.println("Please input only Y or N");
                            choice = ""; //clear input
                        }
                    }
                }//end of duplicate checking
                else {continue;}
            }appendfile(fileName,string);

        } catch (IOException e){
            System.out.println("Error: File cannot be read");
        }
    }


    
    public static void appendfile(String fileName, String string){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            // Check if the last line is empty
            boolean lastLineIsEmpty = false;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    lastLineIsEmpty = true;
                } else {
                    lastLineIsEmpty = false;
                }
            }
            reader.close();

            if (!lastLineIsEmpty && !string.isEmpty()) {
                writer.newLine();
            }

            writer.append(string);
            writer.close();
            delEmptyLine(fileName);

            System.out.println("Data added successfully");
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }

    }

    public static void overwrite(String fileName, String string){
        try {
            List<String> content = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

            reader.close();

            // find the duplicate
            String[] stringsplit = string.split("\\|");
            String newtaskName = stringsplit[1];
            int index = -1; // duplicate index
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
                content.remove(index);
                content.add(string);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false)); // append false
            for (String writeline : content) {
                writer.write(writeline);
                writer.newLine();
            }
            writer.close();
            delEmptyLine(fileName);
            System.out.println("Overwritten");
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }

    }
    public static boolean isEmpty(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            if (reader.readLine() == null) {return true;}
            else {return false;}
        }catch (Exception e){
            return true;
        }
    }

    public static boolean delEmptyLine (String taskName){
        try {
            List<String> content = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("Task.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()){continue;}
                content.add(line);
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("Task.txt", false)); // append false
            for (String writeline : content) {
                writer.write(writeline);
                writer.newLine();
            }
            writer.close();

            reader = new BufferedReader(new FileReader("Task.txt"));
            String emptycheck;
            while ((emptycheck = reader.readLine())!= null){
                if (emptycheck.isEmpty()){return false;}
            }
            return true;
        } catch (IOException e) {
            System.out.println("File cannot be written");
        }
        return false;
    }

}
