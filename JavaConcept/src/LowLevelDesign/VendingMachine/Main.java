package LowLevelDesign.VendingMachine;

public class Main {
    public static void main(String[] args) {
        Machine machine = new Machine();
        try {

            System.out.println("|");
            System.out.println("filling up the inventory");
            System.out.println("|");

            fillUpInventory(machine);
            displayInventory(machine);

            System.out.println("|");
            System.out.println("clicking on InsertCoinButton");
            System.out.println("|");
            State vendingState = machine.getVendingMachineState();
            vendingState.clickOnInsertCoinButton(machine);

            vendingState = machine.getVendingMachineState();
            System.out.println("current State ="+vendingState);
            vendingState.insertCoin(machine, Coin.NICKEL);
            vendingState.insertCoin(machine, Coin.QUARTER);

            System.out.println("|");
            System.out.println("clicking on ProductSelectionButton");
            System.out.println("|");

            vendingState.clickOnStartProductSelectionButton(machine);

            vendingState = machine.getVendingMachineState();
            vendingState.SelectItem(machine, 102);

            displayInventory(machine);

        }catch (Exception e) {
            // displayInventory(machine);
            System.out.print("Exception reported: " + e.getMessage());
        }
    }
    private static void fillUpInventory(Machine vendingMachine){
        ItemShelf[] slots = vendingMachine.getInventory().getItems();
        for (int i = 0; i < slots.length; i++) {
            Item newItem = new Item();
            if(i >=0 && i<3) {
                newItem.setType(ItemType.COKE);
                newItem.setPrice(12);
            }else if(i >=3 && i<5){
                newItem.setType(ItemType.PEPSI);
                newItem.setPrice(9);
            }else if(i >=5 && i<7){
                newItem.setType(ItemType.JUICE);
                newItem.setPrice(13);
            }else if(i >=7 && i<10){
                newItem.setType(ItemType.SODA);
                newItem.setPrice(7);
            }
            slots[i].setItem(newItem);
            slots[i].setSoldOut(false);
        }
    }

    private static void displayInventory(Machine vendingMachine){
        ItemShelf[] slots = vendingMachine.getInventory().getItems();
        for (int i = 0; i < slots.length; i++) {

            System.out.println("CodeNumber: " + slots[i].getCode() +
                    " Item: " + slots[i].getItem().getType().name() +
                    " Price: " + slots[i].getItem().getPrice() +
                    " isAvailable: " + !slots[i].isSoldOut());
        }
    }
}

/*
Currently Vending machine is in IdleState
|
filling up the inventory
|
CodeNumber: 101 Item: COKE Price: 12 isAvailable: true
CodeNumber: 102 Item: COKE Price: 12 isAvailable: true
CodeNumber: 103 Item: COKE Price: 12 isAvailable: true
CodeNumber: 104 Item: PEPSI Price: 9 isAvailable: true
CodeNumber: 105 Item: PEPSI Price: 9 isAvailable: true
CodeNumber: 106 Item: JUICE Price: 13 isAvailable: true
CodeNumber: 107 Item: JUICE Price: 13 isAvailable: true
CodeNumber: 108 Item: SODA Price: 7 isAvailable: true
CodeNumber: 109 Item: SODA Price: 7 isAvailable: true
CodeNumber: 110 Item: SODA Price: 7 isAvailable: true
|
clicking on InsertCoinButton
|
Hetev
Currently Vending machine is in HashMoneyState
current State =LowLevelDesign.VendingMachine.HashMoneyState@26f67b76
|
clicking on ProductSelectionButton
|
Currently Vending machine is in SelectionState
Returned the change in the Coin Dispense Tray: 18
Currently Vending machine is in DispenseState
Product has been dispensed
CodeNumber: 101 Item: COKE Price: 12 isAvailable: true
CodeNumber: 102 Item: COKE Price: 12 isAvailable: false
CodeNumber: 103 Item: COKE Price: 12 isAvailable: true
CodeNumber: 104 Item: PEPSI Price: 9 isAvailable: true
CodeNumber: 105 Item: PEPSI Price: 9 isAvailable: true
CodeNumber: 106 Item: JUICE Price: 13 isAvailable: true
CodeNumber: 107 Item: JUICE Price: 13 isAvailable: true
CodeNumber: 108 Item: SODA Price: 7 isAvailable: true
CodeNumber: 109 Item: SODA Price: 7 isAvailable: true
CodeNumber: 110 Item: SODA Price: 7 isAvailable: true
 */
