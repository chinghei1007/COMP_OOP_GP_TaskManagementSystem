package hk.edu.polyu.comp.comp2021;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
public class CreateTaskTest {

    @Test
    public void createSimpleTask() {
        String fileName = "Task.txt";
        String userInput = "t10\nDescription1\n2\nN\nY\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CreateTask.createSimpleTask(fileName);
        DeleteTask.delTask(fileName,"t10");
        String output = outputStream.toString();
        String expected = "Creating Simple Task\n\n\tEnter task name: \n\tPlease enter the description of the task: \n\tPlease enter the duration of the task: \tWould you like to add any prerequisite tasks?(y/n)\n\nYour task information is as follows\nTask name: t10\nDescription: Description1\nDuration: 2.0 hrs\nPrerequitsite: none\n\nIs this correct? (Y/N): Data added successfully\nSuccess";
        //String expected = "Creating Simple Task\n\n\tEnter task name: \n\n\tPlease enter the description of the task: \n\n\tPlease enter the duration of the task: \n\tWould you like to add any prerequisite tasks?(y/n)\n\nYour task information is as follows\nTask name :t1\nDescription: Description1\nDuration: 2.0 hrs\nPrerequisite: none\n\nIs this correct? (Y/N): \n";
        output = output.trim().replaceAll("\\R","\n");
        expected = expected.trim().replaceAll("\\R","\n");
        assertEquals(expected,output);
    }

    @Test
    public void checkExist() {
        String fileName = "Task.txt";
        String[] arr = new String[2];
        arr[0] = "asfss";arr[1] = "afokd";
        boolean result =CreateTask.checkExist(fileName,arr);
        assertTrue(result);
    }

    @Test
    public void testCheckExist() {
        String fileName = "Task.txt";
        boolean result = CreateTask.checkExist(fileName,"asfss");
        assertTrue(result);
    }

    @Test
    public void createCompositeTask() {
        String fileName = "Task.txt";
        String userInput = "t00\nDescription\nt5,c2\ny\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CreateTask.createCompositeTask(fileName);
        DeleteTask.delTask(fileName,"t00");
        String output = outputStream.toString();
        String expected = "Creating Composite Task\n" +
                "\n" +
                "\tEnter task name: \n" +
                "\tPlease enter the description of the task: Would you like to add any prerequisite tasks?\n" +
                "Here are a list of all your tasks\n" +
                "Simple Task\n" +
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
                "Task Name: t5 Description: asd Prerequisites: 6.0 If you have more than one task, please separate them with comma\n" +
                "t5\n" +
                "c2\n" +
                "\n" +
                "Your task information is as follows\n" +
                "Task name: t00\n" +
                "Description: Description\n" +
                "Task Group: t5,c2\n" +
                "\n" +
                "Is this correct? (Y/N): Data added successfully\n" +
                "Success";
        output = output.trim().replaceAll("\\R","\n");
        expected = expected.trim().replaceAll("\\R","\n");
        assertEquals(expected,output);
    }
}