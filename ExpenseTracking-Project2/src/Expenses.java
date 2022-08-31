import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

//Class1: The expenses class
public class Expenses {
    //Fields
    private String type;
    private Date date;
    private String time;
    private String goodsOrServices;
    private double cost;

    //Constructor
    Expenses(String type, Date date, String time, String goodsOrServices, double cost){
        this.type = type;
        this.date = date;
        this.time = time;
        this.goodsOrServices = goodsOrServices;
        this.cost = cost;
    }

    //This method writes the information that the user gives into a text file so it can be saved and loaded later on
    public void writeToFile(String fileName) throws IOException {
        //Filewriter and buffer writer classes are used to write the info of our class into a txt file
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        //Different symbols are used to allow us to go through this info later on
        bw.write(type + ",\r");
        bw.write(date.getMonth() + "|" + date.getDate() + "=" +  date.getYear() + "]\r");
        bw.write(time + "[\r");
        bw.write(goodsOrServices + "{\r");
        bw.write(cost + "}\r");
        bw.write(";\r");
        bw.close();
    }

    public boolean compareExpenses(Expenses e){
        //This method compares expenses so that we do not get 2 identical expenses by accident
        if((this.type+this.date+this.time+this.goodsOrServices+this.cost).equals(e.type+e.date+e.time+e.goodsOrServices+e.cost)){
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
    public String getGoodsOrServices() {
        return goodsOrServices;
    }
    public double getCost() {
        return cost;
    }

    //toString Method
    public String toString(){
        return goodsOrServices;
    }
}
