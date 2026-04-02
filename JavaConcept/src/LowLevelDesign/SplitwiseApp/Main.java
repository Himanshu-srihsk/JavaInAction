package LowLevelDesign.SplitwiseApp;

public class Main {
    public static void main(String[] args) {
        Splitwise splitwise = new Splitwise();
        splitwise.demo();
    }
}
/*
paidByUserExpenseSheet:LowLevelDesign.SplitwiseApp.UserExpenseBalanceSheet@15975490
paidByUserExpenseSheet:LowLevelDesign.SplitwiseApp.UserExpenseBalanceSheet@6537cf78
---------------------------------------
Balance sheet of user : U1001
TotalYourExpense: 700.0
TotalGetBack: 600.0
TotalYourOwe: 400.0
TotalPaymnetMade: 900.0
userID:U2001 YouGetBack:300.0 YouOwe:400.0
userID:U3001 YouGetBack:300.0 YouOwe:0.0
---------------------------------------
---------------------------------------
Balance sheet of user : U2001
TotalYourExpense: 400.0
TotalGetBack: 400.0
TotalYourOwe: 300.0
TotalPaymnetMade: 500.0
userID:U1001 YouGetBack:400.0 YouOwe:300.0
---------------------------------------
---------------------------------------
Balance sheet of user : U3001
TotalYourExpense: 300.0
TotalGetBack: 0.0
TotalYourOwe: 300.0
TotalPaymnetMade: 0.0
userID:U1001 YouGetBack:0.0 YouOwe:300.0
---------------------------------------
 */