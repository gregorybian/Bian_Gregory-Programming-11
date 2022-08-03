import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }
    //Effects: returns what the deposit was, when it was and what account it was into when the deposit class is prompted
    public String toString(){
        return "Deposit of: $" + this.amount + " Date: " + this.date + " into account: " + this.account;
    }
}
