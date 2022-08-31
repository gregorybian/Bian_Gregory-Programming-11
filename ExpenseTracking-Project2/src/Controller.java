import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    //These are the fields of the first tab in the gui, the expense tab
    public CheckBox cbxFood;
    public CheckBox cbxHousing;
    public CheckBox cbxTransportation;
    public CheckBox cbxOther;
    public DatePicker txtFieldDate;
    public TextField txtFieldTime;
    public TextField txtFieldGoodsOrServices;
    public TextField txtFieldCost;
    public Button btnSaveExpense;
    public Label ErrorCode1;
    private String type = "";

    //Tab 2, Income
    public CheckBox cbxJob;
    public CheckBox cbxSideHustle;
    public CheckBox cbxGift;
    public CheckBox cbxOther2;
    public DatePicker txtFieldDateIncome;
    public TextField txtFieldTimeIncome;
    public TextField txtIncomeSource;
    public TextField txtFieldAmount;
    public Button btnSaveIncome;
    public Label ErrorCode2;
    private String type2 = "";

    //Tab 3, display information
    public CheckBox cbxAllSpendage;
    public CheckBox cbxYearlySpendage;
    public CheckBox cbxMonthlySpendage;
    public ListView listViewExpenses;
    public ListView listViewIncome;
    public Label lblTypeofExpense;
    public Label lblDateExpense;
    public Label lblTimeExpense;
    public Label lblGoodsorServicesExpense;
    public Label lblCostExpense;
    public Label lblTypeOfIncome;
    public Label lblDateIncome;
    public Label lblTimeIncome;
    public Label lblIncomeSource;
    public Label lblAmountIncome;
    public Label lblTotalExpenses;
    public Label lblTotalIncome;
    public Label lblSurplusOrDeficit;
    public Label lblSurplusOrDeficit2;
    private ArrayList<Expenses> allExpenses = new ArrayList<>();
    private ArrayList<Income> allIncome = new ArrayList<>();
    private String type3 = "";
    private double totalExpenses = 0;
    private double totalIncome = 0;


    //Methods

    //These methods are for the expense tab in the program

    //These first 4 methods are so that the user could only select 1 checkbox at a time
    //It also sets the String type equal to whatever type of expense the user chose so that we can have it as a String for later on
    public void selectFoodExpense(ActionEvent actionEvent) {
        cbxHousing.setSelected(false);
        cbxOther.setSelected(false);
        cbxTransportation.setSelected(false);
        type = "Food";
    }

    public void selectHousingExpense(ActionEvent actionEvent) {
        cbxFood.setSelected(false);
        cbxOther.setSelected(false);
        cbxTransportation.setSelected(false);
        type = "Housing";
    }

    public void selectTransportationExpense(ActionEvent actionEvent) {
        cbxHousing.setSelected(false);
        cbxOther.setSelected(false);
        cbxFood.setSelected(false);
        type = "Transportation";
    }

    public void selectOtherExpense(ActionEvent actionEvent) {
        cbxHousing.setSelected(false);
        cbxFood.setSelected(false);
        cbxTransportation.setSelected(false);
        type = "Other";
    }
    //This method will save all the information of the expense into a txt file so that we can load it later on
    public void saveExpense(ActionEvent actionEvent) throws IOException {
        //This if statement checks if any of the fields are empty, if they are, an error code pops up telling the user to fill in all the required information
        if (txtFieldCost.getText().isEmpty()||txtFieldDate.getEditor().getText().isEmpty()||txtFieldTime.getText().isEmpty()||txtFieldGoodsOrServices.getText().isEmpty()){
            ErrorCode1.setText("Please fill in all the required information");
        }
        else if (!cbxFood.isSelected() && !cbxHousing.isSelected() && !cbxTransportation.isSelected() && !cbxOther.isSelected()){
            ErrorCode1.setText("Please fill in all the required information");
        }
        else{
            //The following code creates a new date which is set to the date that the user uses the program
            //If the date the user chooses is after the date that they use the program an error pops up saying they can't pay for something in the future
            ErrorCode1.setText("");
            Date temp = new Date();
            Date compare = new Date(temp.getYear()+1900,temp.getMonth(),temp.getDate());
            //With the date chooser in the scenebuilder, it creates a date with all the information in the string so in order to get the date, month and year, we can substring through each part of the string and choose the date, month and year
            int year = Integer.parseInt(txtFieldDate.getEditor().getText().substring(6,10));
            int month = Integer.parseInt(txtFieldDate.getEditor().getText().substring(3,5))-1;
            int day = Integer.parseInt(txtFieldDate.getEditor().getText().substring(0,2));
            Date date = new Date(year, month, day);
            if (date.after(compare)){
                ErrorCode1.setText("You can not pay for something in the future");
            }
            else{
                //If the month and year are the same, the program puts the information into a txt file using the writeToFile method
                if (date.getMonth() == compare.getMonth() && date.getYear() == compare.getYear()) {
                    Expenses expense = new Expenses(type, date, txtFieldTime.getText(), txtFieldGoodsOrServices.getText(), Double.parseDouble(txtFieldCost.getText()));
                    expense.writeToFile("MonthlyExpenses.txt");
                }
                //If the years are the same, it puts it into the yearly txt file
                if (date.getYear() == compare.getYear()) {
                    Expenses expense = new Expenses(type, date, txtFieldTime.getText(), txtFieldGoodsOrServices.getText(), Double.parseDouble(txtFieldCost.getText()));
                    expense.writeToFile("YearlyExpenses.txt");
                }
                //All the information also gets put into the all text file
                Expenses expense = new Expenses(type, date, txtFieldTime.getText(), txtFieldGoodsOrServices.getText(), Double.parseDouble(txtFieldCost.getText()));
                expense.writeToFile("AllExpenses.txt");
                //At the end all the fields are reset if the user wants to add another expense
                cbxFood.setSelected(false);
                cbxHousing.setSelected(false);
                cbxTransportation.setSelected(false);
                cbxOther.setSelected(false);
                txtFieldDate.getEditor().setText("");
                txtFieldTime.setText("");
                txtFieldGoodsOrServices.setText("");
                txtFieldCost.setText("");
                ErrorCode1.setText("");
            }

        }
    }

    //These methods are for the income tab in the program

    //These first 4 methods are so that the user could only select 1 checkbox at a time
    //It also sets the String type2 equal to whatever type of income the user chose so that we can have it as a String for later on
    public void selectJobIncome(ActionEvent actionEvent) {
        cbxSideHustle.setSelected(false);
        cbxGift.setSelected(false);
        cbxOther2.setSelected(false);
        type2 = "Job";
    }

    public void selectSideHustleIncome(ActionEvent actionEvent) {
        cbxJob.setSelected(false);
        cbxGift.setSelected(false);
        cbxOther2.setSelected(false);
        type2 = "SideHustle";
    }

    public void selectGiftIncome(ActionEvent actionEvent) {
        cbxSideHustle.setSelected(false);
        cbxJob.setSelected(false);
        cbxOther2.setSelected(false);
        type2 = "Gift";
    }

    public void selectOtherIncome(ActionEvent actionEvent) {
        cbxSideHustle.setSelected(false);
        cbxGift.setSelected(false);
        cbxJob.setSelected(false);
        type2 = "Other2";
    }

    //This method will save all the information of the income into a txt file so that we can load it later on
    public void saveIncome(ActionEvent actionEvent) throws IOException {
        //This if statement checks if any of the fields are empty, if they are, an error code pops up telling the user to fill in all the required information
        if (txtFieldAmount.getText().isEmpty()||txtFieldDateIncome.getEditor().getText().isEmpty()||txtFieldTimeIncome.getText().isEmpty()||txtIncomeSource.getText().isEmpty()){
            ErrorCode2.setText("Please fill in all the required information");
        }
        else if (!cbxJob.isSelected() && !cbxSideHustle.isSelected() && !cbxGift.isSelected() && !cbxOther2.isSelected()){
            ErrorCode2.setText("Please fill in all the required information");
        }
        else{
            //The following code creates a new date which is set to the date that the user uses the program
            //If the date the user chooses is after the date that they use the program an error pops up saying they can't pay for something in the future
            ErrorCode2.setText("");
            Date temp = new Date();
            Date compare = new Date(temp.getYear()+1900,temp.getMonth(),temp.getDate());
            //With the date chooser in the scenebuilder, it creates a date with all the information in the string so in order to get the date, month and year, we can substring through each part of the string and choose the date, month and year
            int year = Integer.parseInt(txtFieldDateIncome.getEditor().getText().substring(6,10));
            int month = Integer.parseInt(txtFieldDateIncome.getEditor().getText().substring(3,5))-1;
            int day = Integer.parseInt(txtFieldDateIncome.getEditor().getText().substring(0,2));
            Date date = new Date(year, month, day);
            if (date.after(compare)){
                ErrorCode2.setText("Please don't add income in advance");
            }
            else{
                //If the month and year are the same, the program puts the information into a txt file using the writeToFile method
                if (date.getMonth() == compare.getMonth() && date.getYear() == compare.getYear()) {
                    Income income= new Income(type2, date, txtFieldTimeIncome.getText(), txtIncomeSource.getText(), Double.parseDouble(txtFieldAmount.getText()));
                    income.writeToFile("MonthlyIncome.txt");
                }
                //If the years are the same, it puts it into the yearly txt file
                if (date.getYear() == compare.getYear()) {
                    Income income= new Income(type2, date, txtFieldTimeIncome.getText(), txtIncomeSource.getText(), Double.parseDouble(txtFieldAmount.getText()));
                    income.writeToFile("YearlyIncome.txt");
                }
                //All the information also gets put into the all text file
                Income income= new Income(type2, date, txtFieldTimeIncome.getText(), txtIncomeSource.getText(), Double.parseDouble(txtFieldAmount.getText()));
                income.writeToFile("AllIncome.txt");
                //At the end all the fields are reset if the user wants to add another expense
                cbxJob.setSelected(false);
                cbxSideHustle.setSelected(false);
                cbxGift.setSelected(false);
                cbxOther2.setSelected(false);
                txtFieldDateIncome.getEditor().setText("");
                txtFieldTimeIncome.setText("");
                txtIncomeSource.setText("");
                txtFieldAmount.setText("");
                ErrorCode2.setText("");
            }

        }
    }

    //These methods are for the display tab in the program
    //These 3 methods are so that you can only choose 1 checkbox at a time, it also sets type3 equal to the type of time they want to see how much they spent
    public void selectAllSpendage(ActionEvent actionEvent) {
        cbxYearlySpendage.setSelected(false);
        cbxMonthlySpendage.setSelected(false);
        type3 = "AllSpendage";

    }

    public void selectYearlySpendage(ActionEvent actionEvent) {
        cbxAllSpendage.setSelected(false);
        cbxMonthlySpendage.setSelected(false);
        type3 = "YearlySpendage";
    }

    public void selectMonthlySpendage(ActionEvent actionEvent) {
        cbxYearlySpendage.setSelected(false);
        cbxAllSpendage.setSelected(false);
        type3 = "MonthlySpendage";
    }
    //This methods loads the information the user put in
    public void loadSpendage(ActionEvent actionEvent) throws IOException {
        //If any of the checkboxes aren't selected, and the user clicks load, the GUI will simply not work
        if (!cbxAllSpendage.isSelected() && !cbxMonthlySpendage.isSelected() && !cbxYearlySpendage.isSelected()){
            listViewExpenses.setDisable(true);
            listViewIncome.setDisable(true);
        }
        //If the user inputed everything correctly, all the displaying infor gets reset to blank in case the user has already used the GUI
        else {
            lblTypeofExpense.setText("");
            lblDateExpense.setText("");
            lblTimeExpense.setText("");
            lblGoodsorServicesExpense.setText("");
            lblCostExpense.setText("");
            listViewExpenses.setDisable(false);
            lblTypeOfIncome.setText("");
            lblDateIncome.setText("");
            lblTimeIncome.setText("");
            lblIncomeSource.setText("");
            lblAmountIncome.setText("");
            listViewIncome.setDisable(false);
            listViewExpenses.getItems().clear();
            listViewIncome.getItems().clear();
            totalExpenses = 0;
            totalIncome = 0;

            //This is for if the user chooses the all spendage checkbox
            if (type3.equals("AllSpendage")){
                //This pulls the information from the AllExpenses text using the CreateItem class and puts it into an array list. If that arraylist is empty, it disables the list
                allExpenses = CreateItem.createALlItems("AllExpenses.txt");
                if (allExpenses.isEmpty()){
                    listViewExpenses.setDisable(true);
                }
                //This goes through the arraylist of expenses that was pulled from the text file and adds it the the listview, it also pulls the cost from each of the expenses and adds then displays the total expenses
                else{
                    for (Expenses E : allExpenses){
                        listViewExpenses.getItems().add(E);
                        totalExpenses += E.getCost();
                        lblTotalExpenses.setText(Double.toString(totalExpenses));
                    }
                }
                //Same as Expenses but just with Income
                allIncome = CreateItem.createALlItems("AllIncome.txt");

                if (allIncome.isEmpty()){
                    listViewIncome.setDisable(true);
                }
                else{
                    for(Income I : allIncome){
                        listViewIncome.getItems().add(I);
                        totalIncome += I.getAmount();
                        lblTotalIncome.setText(Double.toString(totalIncome));
                    }
                }
            }
            //This code gets run if the user chooses yearly spendage
            else if (type3.equals("YearlySpendage")){
                //Same code as the all spendage just pulls from the yearly expenses file instead of all spendage
                allExpenses = CreateItem.createALlItems("YearlyExpenses.txt");

                if (allExpenses.isEmpty()){
                    listViewExpenses.setDisable(true);
                }
                else{
                    for (Expenses E : allExpenses){
                        listViewExpenses.getItems().add(E);
                        totalExpenses += E.getCost();
                        lblTotalExpenses.setText(Double.toString(totalExpenses));
                    }
                }
                allIncome = CreateItem.createALlItems("YearlyIncome.txt");

                if (allIncome.isEmpty()){
                    listViewIncome.setDisable(true);
                }
                else {
                    for(Income I : allIncome){
                        listViewIncome.getItems().add(I);
                        totalIncome += I.getAmount();
                        lblTotalIncome.setText(Double.toString(totalIncome));
                    }
                }
            }
            //Finally this code is run if the user chooses monthly spendage
            else if (type3.equals("MonthlySpendage")){
                //Same code as the all spendage just pulls from the monthly spendage text instead
                allExpenses = CreateItem.createALlItems("MonthlyExpenses.txt");

                if (allExpenses.isEmpty()){
                    listViewExpenses.setDisable(true);
                }
                else{
                    for (Expenses E : allExpenses){
                        listViewExpenses.getItems().add(E);
                        totalExpenses += E.getCost();
                        lblTotalExpenses.setText(Double.toString(totalExpenses));
                    }
                }
                allIncome = CreateItem.createALlItems("MonthlyIncome.txt");

                if (allIncome.isEmpty()){
                    listViewIncome.setDisable(true);
                }
                else{
                    for(Income I : allIncome){
                        listViewIncome.getItems().add(I);
                        totalIncome += I.getAmount();
                        lblTotalIncome.setText(Double.toString(totalIncome));
                    }
                }
            }
            //The total expenses and total Income is stored in a double and is displayed to a label so the user can see how much they made and spent
            lblTotalExpenses.setText(Double.toString(totalExpenses));
            lblTotalIncome.setText(Double.toString(totalIncome));

            //If the user spent more than they made, the program says their total deficit and sets that text to red
            if(totalExpenses > totalIncome){
                lblSurplusOrDeficit.setText("You are in a Deficit of " + (totalExpenses - totalIncome) + "$");
                lblSurplusOrDeficit.setTextFill(Paint.valueOf("red"));
            }
            //If the user made more than they spent, the program says their total Surplus and sets that text to green
            else if(totalExpenses < totalIncome){
                lblSurplusOrDeficit.setText("You are in a Surplus of " + (totalIncome - totalExpenses) + "$");
                lblSurplusOrDeficit.setTextFill(Paint.valueOf("green"));
            }
            //If the user spend as much as they made, it just says they are in neither a surplus nor a deficit and set that text to normal
            else if(totalExpenses == totalIncome){
                lblSurplusOrDeficit.setText("You are in neither a Surplus");
                lblSurplusOrDeficit2.setText("nor a Deficit");
                lblSurplusOrDeficit.setTextFill(Paint.valueOf("black"));
            }
        }
    }

    public void displayExpense(MouseEvent mouseEvent) {
        //This method displays the expense information when it is clicked on
        Expenses temp;
        temp = (Expenses) listViewExpenses.getSelectionModel().getSelectedItem();
        lblTypeofExpense.setText(temp.getType());
        lblDateExpense.setText(temp.getDate().getMonth() + 1 + "/" + temp.getDate().getDate()+"/" + temp.getDate().getYear());
        lblTimeExpense.setText(temp.getTime());
        lblGoodsorServicesExpense.setText(temp.getGoodsOrServices());
        lblCostExpense.setText(Double.toString(temp.getCost()));
    }

    public void displayIncome(MouseEvent mouseEvent) {
        //This method displays the income information when it is clicked on
        Income temp;
        temp = (Income) listViewIncome.getSelectionModel().getSelectedItem();
        lblTypeOfIncome.setText(temp.getType());
        lblDateIncome.setText(temp.getDate().getMonth() + 1 + "/" + temp.getDate().getDate()+"/" + temp.getDate().getYear());
        lblTimeIncome.setText(temp.getTime());
        lblIncomeSource.setText(temp.getIncomeSource());
        lblAmountIncome.setText(Double.toString(temp.getAmount()));
    }
}
