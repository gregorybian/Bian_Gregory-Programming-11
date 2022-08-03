import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        //create default constructor
        this.accountNumber = 1;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        this.checkBalance = 0.0;
        this.savingBalance = 0.0;
        this.savingRate = 0.0;
        this.name = "Customer";

    }
    //Constructor
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.name = name;
        this.accountNumber = accountNumber;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        deposit(checkDeposit, new Date(), CHECKING);
        deposit(savingDeposit, new Date(), SAVING);

    }

    //Requires: double, amount/ date/ string, account
    //Modifies: this, deposits
    //Effects: Returns the balance of money for the account that the money is being added to. Else do nothing.
    public double deposit(double amt, Date date, String account){
        //Checks if deposit is mot negative
        if (amt >= 0){
            //Adds deposit to the deposit array into the appropriate account
            if(account.equals(CHECKING)) {
                this.deposits.add(new Deposit(amt, date, CHECKING));
                this.checkBalance += amt;
                return this.checkBalance;
            } else if (account.equals(SAVING)){
                this.deposits.add(new Deposit(amt, date, CHECKING));
                this.savingBalance += amt;
                return this.savingBalance;
            } else{
                return 0;
            }
        }
        else {
            return 0;
        }

    }

    //Requires: double, amount/ date/ string, account
    //Modifies: this, withdrawals
    //Effects: Returns the balance of money for the account that the money is being taken out of. Else do nothing
    public double withdraw(double amt, Date date, String account) {
        //Checks that amt is not neg
        if (amt >= 0) {
            //Checks that it is not an overdraft and withdraws from appropriate account
            if ((account.equals(CHECKING) && checkOverdraft(amt, CHECKING)) == true) {
                this.withdraws.add(new Withdraw(amt, date, CHECKING));
                this.checkBalance -= amt;
                return this.checkBalance;
            } else if ((account.equals(SAVING) && checkOverdraft(amt, SAVING)) == true) {
                this.withdraws.add(new Withdraw(amt, date, SAVING));
                this.savingBalance -= amt;
                return this.checkBalance;
            } else {
                return 0;
            }
        } else{
            return 0;
        }
    }
    //Requires: double, amount/ string, account
    //Effects: Checks to see if the withdrawal only allows the account to be -100$ in debt. If it is return true, if not return false.
    private boolean checkOverdraft(double amt, String account){
        if (account.equals(CHECKING)){
            return this.checkBalance - amt >= OVERDRAFT;
        }
        else if (account.equals(SAVING)){
            return this.savingBalance - amt >= OVERDRAFT;
        }
        else{
            return false;
        }
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

    //Getters to use for test


    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }
    public ArrayList<Withdraw> getWithdraws() {
        return withdraws;
    }
    public double getCheckBalance() {
        return checkBalance;
    }
    public double getSavingBalance() {
        return savingBalance;
    }
}
