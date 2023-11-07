package hk.edu.polyu.comp.comp2021;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PrintTest {

    @Test
    public void printMenu(){
        String userInput = "7\n5\n"; //out of range choice, and return choice
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Print.print("Task.txt");

        String output = outputStream.toString().trim().replaceAll("\\R", "\n");
        String expected = "Which would you like to print?\n" +
                "\t1. All Tasks\n" +
                "\t2. All Simple Tasks\n" +
                "\t3. All Composite Tasks\n" +
                "\t4. Information of one task\n" +
                "\t5. Return to the previous menu\n" +
                "Please enter your choice\n" +
                "Error: Please input numbers in range\n" +
                "\n" +
                "\n" +
                " Which would you like to print?\n" +
                "\t1. All Tasks\n" +
                "\t2. All Simple Tasks\n" +
                "\t3. All Composite Tasks\n" +
                "\t4. Information of one task\n" +
                "\t5. Return to the previous menu\n" +
                "Please enter your choice".trim().replaceAll("\\R", "\n");

        assertEquals(expected,output);
    }

    @Test
    public void printCompositeTask() {
        String fileName = "Task.txt";
        Print.printAllTasks(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Print.printCompositeTask(fileName);
        String output = outputStream.toString();
        String expected = "Composite Task\n" +
                "\n" +
                "Task Name: rt2 Description: t2 Prerequisites: t1,poiou \n" +
                "Task Name: t5 Description: asd Prerequisites: 6.0";
        output = output.trim().replaceAll("\\R" , "\n");
        expected = expected.trim().replaceAll("\\R" , "\n");
        assertEquals(expected,output);

    }

    @Test
    public void printSimpleTask() {
        String fileName = "Task.txt";
        Print.printAllTasks(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Print.printSimpleTask(fileName);
        String output = outputStream.toString();
        String expected = "Simple Task\n" +
                "\n" +
                "Task Name: afokd\n" +
                "Description: ads\n" +
                "Duration: 5.0\n" +
                "Prerequisites: None\n" +
                "\n" +
                "Task Name: asfss\n" +
                "Description: f\n" +
                "Duration: 5.0\n" +
                "Prerequisites: ok\n" +
                "\n" +
                "Task Name: poiou\n" +
                "Description: 1\n" +
                "Duration: 1.0\n" +
                "Prerequisites: asfss,afokd\n" +
                "\n" +
                "Task Name: ok\n" +
                "Description: aa\n" +
                "Duration: 5.0\n" +
                "Prerequisites: t5\n" +
                "\n" +
                "Task Name: t4\n" +
                "Description: t4\n" +
                "Duration: 4.0\n" +
                "Prerequisites: asfss\n" +
                "\n" +
                "Task Name: c2\n" +
                "Description: asd\n" +
                "Duration: 6.0\n" +
                "Prerequisites: None\n" +
                "\n" +
                "Task Name: t1\n" +
                "Description: t1\n" +
                "Duration: 1.0\n" +
                "Prerequisites: t5";
        output = output.trim().replaceAll("\\R" , "\n");
        expected = expected.trim().replaceAll("\\R" , "\n");
        assertEquals(expected,output);

    }

    @Test
    public void printTaskNameOnly() {
        String fileName = "Task.txt";
        Print.printAllTasks(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Print.printSimpleTaskNameOnly(fileName);
        String output = outputStream.toString();
        String expected = "Simple Task\n" +
                "Simple Task: afokd\n" +
                "Simple Task: asfss\n" +
                "Simple Task: poiou\n" +
                "Simple Task: ok\n" +
                "Simple Task: t4\n" +
                "Simple Task: c2\n" +
                "Simple Task: t1";
        output = output.trim().replaceAll("\\R" , "\n");
        expected = expected.trim().replaceAll("\\R" , "\n");
        assertEquals(expected,output);

    }

    @Test
    public void printAllTasks() {
        String fileName = "Task.txt";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Print.printAllTasks(fileName);
        String output = outputStream.toString();
        String expected = "Simple Task\n" +
                "\n" +
                "Task Name: afokd Description: ads Duration: 5.0 Prerequisites: None \n" +
                "Task Name: asfss Description: f Duration: 5.0 Prerequisites: ok \n" +
                "Task Name: poiou Description: 1 Duration: 1.0 Prerequisites: asfss,afokd \n" +
                "Task Name: ok Description: aa Duration: 5.0 Prerequisites: t5 \n" +
                "Task Name: t4 Description: t4 Duration: 4.0 Prerequisites: asfss \n" +
                "Task Name: c2 Description: asd Duration: 6.0 Prerequisites: None \n" +
                "Task Name: t1 Description: t1 Duration: 1.0 Prerequisites: t5 \n" +
                "Composite Task\n" +
                "Task Name: rt2 Description: t2 Prerequisites: t1,poiou \n" +
                "Task Name: t5 Description: asd Prerequisites: 6.0";
        output = output.trim().replaceAll("\\R" , "\n");
        expected = expected.trim().replaceAll("\\R" , "\n");
        assertEquals(expected,output);
    }

    @Test
    public void printOneTask() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Print.printOneTask("Task.txt", "t1");

        String output = outputStream.toString().trim().replaceAll("\\R", "\n");
        String expected = "Task found\n" +
                "Name: t1\n" +
                "Description: t1\n" +
                "Duration: 1.0 Prerequisite: t5".trim().replaceAll("\\R", "\n");

        assertEquals(expected,output);
    }

    @Test
    public void printOneTask_noTaskArgument(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut((new PrintStream(outputStream)));
        String userInput = "t1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        Print.printOneTask("Task.txt");

        String output = outputStream.toString().trim().replaceAll("\\R", "\n");
        String expected = "Please input the task you wish to find\n" +
                "Task found\n" +
                "Name: t1\n" +
                "Description: t1\n" +
                "Duration: 1.0 Prerequisite: t5".trim().replaceAll("\\R", "\n");

        assertEquals(expected,output);

    }

}