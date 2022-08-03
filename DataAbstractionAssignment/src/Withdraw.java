import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Effects: returns what the withdraw was, when it was and into what account when the withdraw class is prompted.
    public String toString(){
        return "Withdrawal of: $" + this.amount + " Date: " + this.date + " into account: " + this.account;
    }
}
