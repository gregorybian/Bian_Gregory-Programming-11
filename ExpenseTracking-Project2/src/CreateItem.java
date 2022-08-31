import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

//Class3: The CreateItem class
public class CreateItem {
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Expenses> allExpenses = new ArrayList<>();
    private static ArrayList<Income> allIncome = new ArrayList<>();
    private static ArrayList<Expenses> monthlyExpenses = new ArrayList<>();
    private static ArrayList<Income> monthlyIncome = new ArrayList<>();
    private static ArrayList<Expenses> yearlyExpenses = new ArrayList<>();
    private static ArrayList<Income> yearlyIncome = new ArrayList<>();


    //This method reads selected files and pulls the information out of the file then recreates it into the original objects
    public static ArrayList createALlItems(String filename) throws IOException {
        //fileReader and bufferReader method to read through the files
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
        String line;
        String itemString = "";
        //going through the file until the reader reads ;
        //Once there, it parses the string and recreates the object from the file
        while ((line = br.readLine()) != null) {
            if (!line.equals(";")) {
                itemString += line;
            } else {
                parseItem(itemString, filename);
                itemString = "";
            }
        }
        //This returns the recreated arraylist of what the user chooses
        if (filename.equals("AllExpenses.txt")) {
            return allExpenses;
        } else if (filename.equals("AllIncome.txt")) {
            return allIncome;
        } else if (filename.equals("MonthlyExpenses.txt")) {
            return monthlyExpenses;
        } else if (filename.equals("MonthlyIncome.txt")) {
            return monthlyIncome;
        } else if (filename.equals("YearlyExpenses.txt")) {
            return yearlyExpenses;
        } else if (filename.equals("YearlyIncome.txt")) {
            return yearlyIncome;
        } else{
            return null;
        }
    }

    //This method goes through each line in the text and finds where each field of the object is then recreates the object
    private static void parseItem(String string, String fileName){
        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        int pos5 = 0;
        int pos6 = 0;
        int pos7 = 0;
        //Setting the field variables equal to nothing for now but later the fields in the text file will replace these variables
        String type = "";
        int month = 0;
        int date = 0;
        int year = 0;
        String time = "";
        String source = "";
        double amount = 0;
        //This goes through the text and checks for symbols that were put after every field of the object. This then sets that field equal to it's variable we created before so that we can recreate the object later on
        for (int i = 0; i<string.length(); i++){
            if(string.substring(i,i+1).equals(",")){
                pos1 = i;
                type = string.substring(0,pos1);
            }else if(string.substring(i,i+1).equals("|")){
                pos2 = i;
                month = Integer.parseInt(string.substring(pos1+1, pos2));
            }
            else if(string.substring(i,i+1).equals("=")){
                pos3 = i;
                date = Integer.parseInt(string.substring(pos2+1, pos3));
            }
            else if(string.substring(i,i+1).equals("]")){
                pos4 = i;
                year = Integer.parseInt(string.substring(pos3+1, pos4));
            }
            else if(string.substring(i,i+1).equals("[")){
                pos5 = i;
                time = string.substring(pos4+1, pos5);
            }
            else if(string.substring(i,i+1).equals("{")){
                pos6 = i;
                source = string.substring(pos5+1, pos6);
            }
            else if(string.substring(i,i+1).equals("}")){
                pos7 = i;
                amount = Double.parseDouble(string.substring(pos6+1, pos7));
            }

        }
        //This recreates the date using java's date class
        Date tempDate = new Date(year, month, date);
        //This recreates both the expense object and the income object
        Expenses expense = new Expenses(type, tempDate, time, source, amount);
        Income income = new Income(type, tempDate, time, source, amount);
        //The following lines of code checks to see if any of the objects are the same in any of the given texts
        boolean same = false;
        if (fileName.equals("AllExpenses.txt")) {
            for(Expenses e: allExpenses){
                if(e.compareExpenses(expense)){
                    same=true;
                    break;
                }
            }
        }
        else if (fileName.equals("MonthlyExpenses.txt")) {
            for(Expenses e: monthlyExpenses){
                if(e.compareExpenses(expense)){
                    same=true;
                    break;
                }
            }
        }
        else if (fileName.equals("YearlyExpenses.txt")) {
            for(Expenses e: yearlyExpenses){
                if(e.compareExpenses(expense)){
                    same=true;
                    break;
                }
            }
        }
        else if (fileName.equals("AllIncome.txt")) {
            for(Income i: allIncome){
                if(i.compareIncome(income)){
                    same=true;
                    break;
                }
            }
        }
        else if (fileName.equals("MonthlyIncome.txt")) {
            for(Income i: monthlyIncome){
                if(i.compareIncome(income)){
                    same=true;
                    break;
                }
            }
        }
        else if (fileName.equals("YearlyIncome.txt")) {
            for(Income i: yearlyIncome){
                if(i.compareIncome(income)){
                    same=true;
                    break;
                }
            }
        }
        //If none of the objects are the same, the object is added to an arraylist according to what type it is
        if(!same){
            if (fileName.equals("AllExpenses.txt")) {
                allExpenses.add(expense);
            } else if (fileName.equals("AllIncome.txt")) {
                allIncome.add(income);
            } else if (fileName.equals("MonthlyExpenses.txt")) {
                monthlyExpenses.add(expense);
            } else if (fileName.equals("MonthlyIncome.txt")) {
                monthlyIncome.add(income);
            } else if (fileName.equals("YearlyExpenses.txt")) {
                yearlyExpenses.add(expense);
            } else if (fileName.equals("YearlyIncome.txt")) {
                yearlyIncome.add(income);
            }
        }


    }

}
