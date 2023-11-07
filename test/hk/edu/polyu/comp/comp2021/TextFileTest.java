package hk.edu.polyu.comp.comp2021;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.*;

public class TextFileTest {

    @Test
    public void writeFile() { //checking append()
        //The same function will be used and tested in the CreateTaskTest
        String fileName = "Task.txt";
        String string = "s|t10|Description|3|,";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TextFile.writeFile(fileName,string);
        DeleteTask.delTask(fileName,"t10");

        String output = outputStream.toString().trim();
        String expected = "Data added successfully\n" +
                "Success";

        output = output.trim().replaceAll("\\R","\n");
        expected = expected.trim().replaceAll("\\R","\n");


        assertEquals(expected,output);
    }
    @Test
    public void writeFileOverwrite(){
    String fileName = "Task.txt";
    //random description
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 7; ++i){
            sb.append((char)(random.nextInt(26)+'A'));
        }
        String inputString = "s|t1|"+sb.toString()+"|5|,";
        String recoverString = "s|t1|t1|1|t5";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String userInput = "y\ny\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        TextFile.writeFile(fileName,inputString);

        inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        TextFile.writeFile(fileName,recoverString);


        String output = outputStream.toString().trim().replaceAll("\\R", "\n");
        String expected = "Duplicate task name found\n" +
                "Do you want to overwrite the existing task? (Y/N)\n" +
                "Overwritten\n" +
                "Duplicate task name found\n" +
                "Do you want to overwrite the existing task? (Y/N)\n" +
                "Overwritten".trim().replaceAll("\\R", "\n");
        assertEquals(expected,output);


    }

    @Test
    public void isEmpty() {
        boolean isEmpty = TextFile.isEmpty("Task.txt");
        assertFalse(isEmpty);
    }


    @Test
    public void clearEmptyLines() {
        assertTrue(TextFile.delEmptyLine("Task.txt"));

    }
}