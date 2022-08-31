import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.RequestWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertFalse;

public class MoneyTrackingTests {
    Expenses expense1;
    Expenses expense2;
    Expenses expense3;
    Income income1;
    Income income2;
    Income income3;
    File expenseFileTest;
    File expenseFileTest2;
    File incomeFileTest;
    File incomeFileTest2;

    @Before
    public void setUp(){
        expenseFileTest = new File("AllExpenses.txt");
        expenseFileTest2 = new File("AllExpenses2.txt");
        expense1 = new Expenses("Food", new Date(2022, 7, 20), "4pm", "Hamburger", 9.99);
        expense2 = new Expenses("Housing", new Date(2021, 4, 7), "1pm", "Rent", 400);
        expense3 = new Expenses("Transportation", new Date(2022, 8, 15), "7pm", "Uber", 30);
        incomeFileTest = new File("AllIncome.txt");
        incomeFileTest2 = new File("AllIncome2.txt");
        income1 = new Income("Job", new Date(2022, 1, 3), "1pm", "ITjob", 9.99);
        income2 = new Income("SideHustle", new Date(2021, 3, 14), "7pm", "Selling Ebooks", 20);
        income3 = new Income("Gift", new Date(2022, 9, 19), "5pm", "Friend Amazon Gift Card", 50);

    }
    @Test
    public void testWriteToFileExpense() throws IOException {
        //Deleting then recreating the file so that it is empty
        expenseFileTest2.delete();
        expenseFileTest2.createNewFile();
        //Checking that the file is empty
        assertEquals(0,expenseFileTest2.length());
        FileReader fr = new FileReader(expenseFileTest2);
        BufferedReader br =new BufferedReader(fr);
        String line="";
        String expenseActual="";
        int lineNumber=0;
        expense1.writeToFile("AllExpenses2.txt");
        //Using filereader and bufferedreader to read through the file with the information of an expense written by the method
        //Adding each line into a string and calculating the number of lines
        while((line=br.readLine())!=null){
            expenseActual+=line;
            lineNumber++;
        }
        String expenseExpected="Food,7|20=2022]4pm[Hamburger{9.99};";
        //Comparing the string of the file with the expected string
        assertEquals(expenseExpected,expenseActual);
        //Comparing the number of lines with the expected number
        assertEquals(6,lineNumber);
        expense2.writeToFile("AllExpenses2.txt");
        //Repeating this with another different expense
        while((line= br.readLine())!=null){
            expenseActual+=line;
            lineNumber++;
        }
        expenseExpected+="Housing,4|7=2021]1pm[Rent{400.0};";
        //Comparing information in the strings
        assertEquals(expenseExpected,expenseActual);

        //Comparing line numbers
        assertEquals(12,lineNumber);
        expense3.writeToFile("AllExpenses2.txt");
        //Reapeating same process for one last time to make sure
        while((line=br.readLine())!=null){
            expenseActual+=line;
            lineNumber++;
        }
        br.close();
        expenseExpected+="Transportation,8|15=2022]7pm[Uber{30.0};";
        //Comparing information in strings
        assertEquals(expenseExpected,expenseActual);
        //Comparing line numbers
        assertEquals(18,lineNumber);
        expenseFileTest2.delete();
        expenseFileTest2.createNewFile();
        //Making sure that there is nothing in the file
        assertEquals(0,expenseFileTest2.length());
    }
    @Test
    public void testWriteToFileIncome() throws IOException {

        //Deleting then recreating the file so that it is empty
        incomeFileTest2.delete();
        incomeFileTest2.createNewFile();
        //Checking that the file is empty
        assertEquals(0,incomeFileTest2.length());
        FileReader fr = new FileReader(incomeFileTest2);
        BufferedReader br =new BufferedReader(fr);
        String line="";
        String incomeActual="";
        int lineNumber=0;
        income1.writeToFile("AllIncome2.txt");
        //Using filereader and bufferedreader to read through the file with the information of an income written by the method
        //Adding each line into a string and calculating the number of lines
        while((line=br.readLine())!=null){
            incomeActual+=line;
            lineNumber++;
        }
        String incomeExpected="Job,1|3=2022]1pm[ITjob{9.99};";
        //Comparing the string of the file with the expected string
        assertEquals(incomeExpected,incomeActual);
        //Comparing the number of lines with the expected number
        assertEquals(6,lineNumber);
        income2.writeToFile("AllIncome2.txt");
        //Repeating this with another different income
        while((line= br.readLine())!=null){
            incomeActual+=line;
            lineNumber++;
        }
        incomeExpected+="SideHustle,3|14=2021]7pm[Selling Ebooks{20.0};";
        //Comparing information in the strings
        assertEquals(incomeExpected,incomeActual);

        //Comparing line numbers
        assertEquals(12,lineNumber);
        income3.writeToFile("AllIncome2.txt");
        //Reapeating same process for one last time to make sure
        while((line=br.readLine())!=null){
            incomeActual+=line;
            lineNumber++;
        }
        br.close();
        incomeExpected+="Gift,9|19=2022]5pm[Friend Amazon Gift Card{50.0};";
        //Comparing information in strings
        assertEquals(incomeExpected,incomeActual);
        //Comparing line numbers
        assertEquals(18,lineNumber);
        incomeFileTest2.delete();
        incomeFileTest2.createNewFile();
        //Making sure that there is nothing in the file
        assertEquals(0,incomeFileTest2.length());
    }
    @Test
    //Testing createAllItems method inside CreateItem classes
    public void testCreateItem() throws IOException{
        //Writing 3 expenses into their file
        expense1.writeToFile("AllExpenses.txt");
        expense2.writeToFile("AllExpenses.txt");
        expense3.writeToFile("AllExpenses.txt");
        ArrayList<Expenses> expenseActual;
        //Using "expenseactual" to house the created expenses by the createAllItems method
        expenseActual = CreateItem.createALlItems("AllExpenses.txt");
        //Creating "expenseExpected" to compare with the newly created arraylist
        ArrayList <Expenses> expenseExpected = new ArrayList();
        expenseExpected.add(expense1);
        expenseExpected.add(expense2);
        expenseExpected.add(expense3);
        //Comparing the information to make sure that the expected and the actual created ones match
        assertEquals(expense1.getType()+expense1.getDate()+expense1.getTime()+expense1.getGoodsOrServices()+expense1.getCost(),expenseActual.get(0).getType()+expenseActual.get(0).getDate()+expenseActual.get(0).getTime()+expenseActual.get(0).getGoodsOrServices()+expenseActual.get(0).getCost());
        assertEquals(expense2.getType()+expense2.getDate()+expense2.getTime()+expense2.getGoodsOrServices()+expense2.getCost(),expenseActual.get(1).getType()+expenseActual.get(1).getDate()+expenseActual.get(1).getTime()+expenseActual.get(1).getGoodsOrServices()+expenseActual.get(1).getCost());
        assertEquals(expense3.getType()+expense3.getDate()+expense3.getTime()+expense3.getGoodsOrServices()+expense3.getCost(),expenseActual.get(2).getType()+expenseActual.get(2).getDate()+expenseActual.get(2).getTime()+expenseActual.get(2).getGoodsOrServices()+expenseActual.get(2).getCost());
        //Comparing the sizes to make sure that there isn't any extra expenses
        assertEquals(expenseExpected.size(), expenseActual.size());

        //Testing for Income
        //Same process as testing for expenses just with income object instead
        incomeFileTest.delete();
        incomeFileTest.createNewFile();
        income1.writeToFile("AllIncome.txt");
        income2.writeToFile("AllIncome.txt");
        income3.writeToFile("AllIncome.txt");
        ArrayList<Income> incomeActual;
        incomeActual = CreateItem.createALlItems("AllIncome.txt");
        ArrayList <Income> incomeExpected = new ArrayList();
        incomeExpected.add(income1);
        incomeExpected.add(income2);
        incomeExpected.add(income3);
        assertEquals(income1.getType()+income1.getDate()+income1.getTime()+income1.getIncomeSource()+income1.getAmount(),incomeActual.get(0).getType()+incomeActual.get(0).getDate()+incomeActual.get(0).getTime()+incomeActual.get(0).getIncomeSource()+incomeActual.get(0).getAmount());
        assertEquals(income2.getType()+income2.getDate()+income2.getTime()+income2.getIncomeSource()+income2.getAmount(),incomeActual.get(1).getType()+incomeActual.get(1).getDate()+incomeActual.get(1).getTime()+incomeActual.get(1).getIncomeSource()+incomeActual.get(1).getAmount());
        assertEquals(income3.getType()+income3.getDate()+income3.getTime()+income3.getIncomeSource()+income3.getAmount(),incomeActual.get(2).getType()+incomeActual.get(2).getDate()+incomeActual.get(2).getTime()+incomeActual.get(2).getIncomeSource()+incomeActual.get(2).getAmount());
        assertEquals(incomeExpected.size(), incomeActual.size());
    }
    @Test
    //Testing the compare method for expenses
    public void testCompareExpenses(){
        Expenses duplicateExpense1=expense1;
        Expenses duplicateExpense2=expense2;
        Expenses duplicateExpense3=expense3;
        //Checking that the expenses when compared to their duplicate return true
        assertTrue(expense1.compareExpenses(duplicateExpense1));
        assertTrue(expense2.compareExpenses(duplicateExpense2));
        assertTrue(expense3.compareExpenses(duplicateExpense3));
        //Checking that the expenses when compared to a different expense return false
        assertFalse(expense1.compareExpenses(duplicateExpense2));
        assertFalse(expense1.compareExpenses(duplicateExpense3));
        assertFalse(expense2.compareExpenses(duplicateExpense1));
        assertFalse(expense2.compareExpenses(duplicateExpense3));
        assertFalse(expense3.compareExpenses(duplicateExpense2));
        assertFalse(expense3.compareExpenses(duplicateExpense1));
    }

    @Test
    //Testing the compare method for income
    public void testCompareIncome(){
        Income duplicateIncome1=income1;
        Income duplicateIncome2=income2;
        Income duplicateIncome3=income3;
        //Checking that the income when compared to their duplicate return true
        assertTrue(income1.compareIncome(duplicateIncome1));
        assertTrue(income2.compareIncome(duplicateIncome2));
        assertTrue(income3.compareIncome(duplicateIncome3));
        //Checking that the income when compared to a different income return false
        assertFalse(income1.compareIncome(duplicateIncome2));
        assertFalse(income1.compareIncome(duplicateIncome3));
        assertFalse(income2.compareIncome(duplicateIncome1));
        assertFalse(income2.compareIncome(duplicateIncome3));
        assertFalse(income3.compareIncome(duplicateIncome2));
        assertFalse(income3.compareIncome(duplicateIncome1));
    }

    @Test
    public void testToStringExpenses(){
        //Create the expected output
        String expectedOutput1 = "Hamburger";
        String expectedOutput2 = "Rent";
        String expectedOutput3 = "Uber";
        //Check to see if the output is the same as the expected output
        assertEquals(expectedOutput1, expense1.toString());
        assertEquals(expectedOutput2, expense2.toString());
        assertEquals(expectedOutput3, expense3.toString());
    }
    @Test
    public void testToStringIncome(){
        //Create the expected output
        String expectedOutput1 = "ITjob";
        String expectedOutput2 = "Selling Ebooks";
        String expectedOutput3 = "Friend Amazon Gift Card";
        //Check to see if the output is the same as the expected output
        assertEquals(expectedOutput1, income1.toString());
        assertEquals(expectedOutput2, income2.toString());
        assertEquals(expectedOutput3, income3.toString());
    }

}
