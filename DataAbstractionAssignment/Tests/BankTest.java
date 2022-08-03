import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class BankTest {
    Customer testCustomer;
    Withdraw testWithdrawChecking;
    Withdraw testWithdrawSavings;
    Deposit testDepositChecking;
    Deposit testDepositSaving;


    @Before
    public void setup(){
        testCustomer = new Customer("Customer", 1, 0, 0);
        testWithdrawChecking = new Withdraw(100.0, new Date(), Customer.CHECKING);
        testWithdrawSavings = new Withdraw(100.0, new Date(), Customer.SAVING);
        testDepositChecking = new Deposit(100, new Date(), Customer.CHECKING);
        testDepositSaving = new Deposit(100, new Date(), Customer.SAVING);

    }
    @Test
    public void testDepositToString(){
        //Create the expected output
        String expectedOutputChecking = "Deposit of: $" + 100.0 + " Date: " + new Date() + " into account: " + Customer.CHECKING;
        //Check that the output is the same as the expected output
        assertEquals(expectedOutputChecking, testDepositChecking.toString());

        //Same but with the savings account
        String expectedOutputSavings = "Deposit of: $" + 100.0 + " Date: " + new Date() + " into account: " + Customer.SAVING;
        //Check that the output is the same as the expected output
        assertEquals(expectedOutputSavings, testDepositSaving.toString());

    }

    @Test
    public  void testWithdrawToString(){
        //Create the expected output
        String expectedOutputChecking = "Withdrawal of: $" + 100.0 + " Date: " + new Date() + " into account: " + Customer.CHECKING;
        //Check that the output is the same as the expected output
        assertEquals(expectedOutputChecking, testWithdrawChecking.toString());

        //Do the same but for savings account
        String expectedOutputSavings = "Withdrawal of: $" + 100.0 + " Date: " + new Date() + " into account: " + Customer.SAVING;
        assertEquals(expectedOutputSavings, testWithdrawSavings.toString());

    }

    @Test
    public void testDeposit(){
        //This is for checking account
        //Check to see that balance is 0
        assertEquals(0.0, testCustomer.getCheckBalance());
        //Check that it does not allow negative deposits
        testCustomer.deposit(-100.0, new Date(), Customer.CHECKING);
        assertEquals(0.0, testCustomer.getCheckBalance());
        //Check that normal deposits work
        testCustomer.deposit(100.0, new Date(), Customer.CHECKING);
        assertEquals(100.0, testCustomer.getCheckBalance());
        assertEquals(3, testCustomer.getDeposits().size());

        //Same test but with savings account
        assertEquals(0.0, testCustomer.getSavingBalance());
        testCustomer.deposit(-100.0, new Date(), Customer.SAVING);
        assertEquals(0.0, testCustomer.getSavingBalance());
        testCustomer.deposit(100.0, new Date(), Customer.SAVING);
        assertEquals(100.0, testCustomer.getSavingBalance());
        assertEquals(4, testCustomer.getDeposits().size());
    }

    @Test
    public void testWithdraws(){
        //Check that balance is at 0
        assertEquals(0.0, testCustomer.getCheckBalance());
        //Put in 100 dollars then withdraw it to see if withdrawing works
        testCustomer.deposit(100.0, new Date(), Customer.CHECKING);
        testCustomer.withdraw(100.0, new Date(), Customer.CHECKING);
        assertEquals( 0.0, testCustomer.getCheckBalance());
        //Check to see that you can't withdraw with more than 100 debt in account
        testCustomer.withdraw(100.0, new Date(), Customer.CHECKING);
        assertEquals( -100.0, testCustomer.getCheckBalance());
        testCustomer.withdraw(1, new Date(), Customer.CHECKING);
        assertFalse(testCustomer.getCheckBalance()==-101.0);
        assertEquals(-100.0,testCustomer.getCheckBalance());
        assertEquals(2, testCustomer.getWithdraws().size());

        //Same test but with savings account
        assertEquals(0.0, testCustomer.getSavingBalance());
        testCustomer.deposit(100.0, new Date(), Customer.SAVING);
        testCustomer.withdraw(100.0, new Date(), Customer.SAVING);
        assertEquals(0.0, testCustomer.getSavingBalance());
        testCustomer.withdraw(100.0, new Date(), Customer.SAVING);
        assertEquals(-100.0,testCustomer.getSavingBalance());
        testCustomer.withdraw(1.0, new Date(), Customer.SAVING);
        assertFalse(testCustomer.getSavingBalance()==-101.0);
        assertEquals(-100.0, testCustomer.getSavingBalance());
        assertEquals(4, testCustomer.getWithdraws().size());
    }
}
