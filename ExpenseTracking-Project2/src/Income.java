import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

//Class2: The Income class
public class Income {
    //Fields
    private String type;
    private Date date;
    private String time;
    private String incomeSource;
    private double amount;

    //Constructor
    Income(String type, Date date, String time, String incomeSource, double amount){
        this.type = type;
        this.date = date;
        this.time = time;
        this.incomeSource = incomeSource;
        this.amount = amount;
    }

    //This method writes the information that the user gives into a text file so it can be saved and loaded later on
    //Requires: string, filename
    //Effects: Takes the instance of the class and puts all the information into a text file
    public void writeToFile(String fileName) throws IOException {
        //Filewriter and buffer writer classes are used to write the info of our class into a txt file
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        //Different symbols are used to allow us to go through this info later on
        bw.write(type + ",\r");
        bw.write(date.getMonth() + "|" + date.getDate() + "=" +  date.getYear() + "]\r");
        bw.write(time + "[\r");
        bw.write(incomeSource + "{\r");
        bw.write(amount + "}\r");
        bw.write(";\r");
        bw.close();
    }
    //Requires: income, i
    //Effects: Returns true if this income is the same as i, otherwise returns false
    public boolean compareIncome(Income i){
        //This method compares income so that we do not get 2 identical incomes by accident
        if((this.type+this.date+this.time+this.incomeSource+this.amount).equals(i.type+i.date+i.time+i.incomeSource+i.amount)){
            return true;
        }
        else{
            return false;
        }
    }

    //Getters
    public String getType() {
        return type;
    }
    public Date getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getIncomeSource() {
        return incomeSource;
    }
    public double getAmount() {
        return amount;
    }

    //toString Method
    //Effects: returns the incomesource when the instance of the class is called
    public String toString(){
        return incomeSource;
    }
}

