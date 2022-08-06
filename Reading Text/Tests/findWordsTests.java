import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class findWordsTests {

    @Test

    public void setup() throws IOException {
        //This adds the text to an array so the tests can use it.

        ArrayList<String> text = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            text.add(line);
        }
        br.close();


        //These are some tests that use different words and see if they return the correct index positions
        ArrayList<Integer> test = Main.findWords(text,"the");
        ArrayList<Integer> expectedTest = new ArrayList<>();
        expectedTest.add(0);
        expectedTest.add(2);
        expectedTest.add(6);
        expectedTest.add(8);
        expectedTest.add(12);
        expectedTest.add(14);
        assertEquals(test, expectedTest);

        ArrayList<Integer> test2 = Main.findWords(text,"and");
        ArrayList<Integer> expectedTest2 = new ArrayList<>();
        expectedTest2.add(0);
        expectedTest2.add(5);
        expectedTest2.add(6);
        expectedTest2.add(8);
        expectedTest2.add(12);
        expectedTest2.add(14);
        assertEquals(test2, expectedTest2);

        ArrayList<Integer> test3 = Main.findWords(text,"code");
        ArrayList<Integer> expectedTest3 = new ArrayList<>();
        expectedTest3.add(8);
        expectedTest3.add(12);
        assertEquals(test3, expectedTest3);

        ArrayList<Integer> test4 = Main.findWords(text,"Al-Jazari");
        ArrayList<Integer> expectedTest4 = new ArrayList<>();
        expectedTest4.add(0);
        assertEquals(test4, expectedTest4);

        ArrayList<Integer> test5 = Main.findWords(text,"1880s");
        ArrayList<Integer> expectedTest5 = new ArrayList<>();
        expectedTest5.add(6);
        assertEquals(test5, expectedTest5);
    }

}
