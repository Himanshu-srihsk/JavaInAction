package LowLevelDesign.AtmService;

public class Main {
    ATM atm;
    User user;
    public static void main(String[] args) {
        Main atmRoom = new Main();
        atmRoom.initialize();


        atmRoom.atm.printCurrentATMStatus();
        atmRoom.atm.getCurrentATMState().insertCard(atmRoom.atm, atmRoom.user.card);
        atmRoom.atm.getCurrentATMState().authenticatePin(atmRoom.atm, atmRoom.user.card, 112211);
        atmRoom.atm.getCurrentATMState().selectOperation(atmRoom.atm, atmRoom.user.card, TransactionType.CASH_WITHDRAWAL);
        atmRoom.atm.getCurrentATMState().cashWithdrawal(atmRoom.atm, atmRoom.user.card, 2700);
        atmRoom.atm.printCurrentATMStatus();
    }
    private void initialize() {

        //create ATM
        atm = ATM.getATMObject();
        atm.setAtmBalance(3500, 1,2,5);

        //create User
        this.user = createUser();
    }

    private User createUser(){

        User user = new User();
        user.setCard(createCard());
        return user;
    }

    private Card createCard(){

        Card card = new Card();
        card.setBankAccount(createBankAccount());
        return card;
    }

    private UserBankAccount createBankAccount() {

        UserBankAccount bankAccount = new UserBankAccount();
        bankAccount.balance = 3000;

        return bankAccount;

    }
}
/*
Balance: 3500
2kNotes: 1
500Notes: 2
100Notes: 5
Card is inserted
enter your card pin number
Please select the Operation
CASH_WITHDRAWAL
BALANCE_CHECK
Please enter the Withdrawal Amount
Please collect your card
Exit happens
Balance: 800
2kNotes: 0
500Notes: 1
100Notes: 3

 */