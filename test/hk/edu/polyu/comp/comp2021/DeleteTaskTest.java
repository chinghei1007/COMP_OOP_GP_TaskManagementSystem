package hk.edu.polyu.comp.comp2021;

import org.junit.Test;
import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DeleteTaskTest {

    @Test
    public void delTaskMenu() {
        String fileName = "Task.txt";
        String userInput = "tDTM\nDescription1\n2\nN\nY\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CreateTask.createSimpleTask(fileName);
        DeleteTask.delTask(fileName,"tDTM");
        String output = outputStream.toString();
        String expected = "Creating Simple Task\n\n\tEnter task name: \n\tPlease enter the description of the task: \n\tPlease enter the duration of the task: \tWould you like to add any prerequisite tasks?(y/n)\n\nYour task information is as follows\nTask name: tDTM\nDescription: Description1\nDuration: 2.0 hrs\nPrerequitsite: none\n\nIs this correct? (Y/N): Data added successfully\nSuccess";
        //String expected = "Creating Simple Task\n\n\tEnter task name: \n\n\tPlease enter the description of the task: \n\n\tPlease enter the duration of the task: \n\tWould you like to add any prerequisite tasks?(y/n)\n\nYour task information is as follows\nTask name :t1\nDescription: Description1\nDuration: 2.0 hrs\nPrerequisite: none\n\nIs this correct? (Y/N): \n";
        output = output.trim().replaceAll("\\R","\n");
        expected = expected.trim().replaceAll("\\R","\n");
        assertEquals(expected,output);
    }

    @Test
    public void delTask() {
        String fileName = "Task.txt";
        TextFile.appendfile(fileName,"s|tDTM|Description1|2|,");
        DeleteTask.delTask(fileName,"tDTM");

        assertFalse(CreateTask.checkExist(fileName,"tDTM"));
    }

    @Test
    public void delTaskAndUpdatePrerequisites() {
        String fileName = "Task.txt";
        TextFile.appendfile(fileName,"s|tDTM|Description1|2|,");
        TextFile.appendfile(fileName,"s|tDTM2|Description2|2|,");
    }
}