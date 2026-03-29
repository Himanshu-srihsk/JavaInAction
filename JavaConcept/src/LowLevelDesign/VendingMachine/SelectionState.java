package LowLevelDesign.VendingMachine;

import java.util.List;

public class SelectionState implements State{
    public SelectionState(){
        System.out.println("Currently Vending machine is in SelectionState");
    }


    public void clickOnInsertCoinButton(Machine m) throws Exception{
        throw new Exception("not allowed operation");
    }
    public void insertCoin(Machine m,Coin coin) throws Exception{
        throw new Exception("not allowed operation");
    }
    public void clickOnStartProductSelectionButton(Machine m)  throws Exception{
        throw new Exception("not allowed operation");
    }
    public void SelectItem(Machine m,int Code) throws Exception{
        //1. get item of this codeNumber
        Item item = m.getInventory().getItem(Code);

        int paidByUser = 0;
        for(Coin coin : m.getCoinList()){
            paidByUser = paidByUser + coin.value;
        }

        if(paidByUser < item.getPrice()) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.getPrice() + " and you paid: " + paidByUser);
            refundCoin(m);
            throw new Exception("insufficient amount");
        }else if(paidByUser >= item.getPrice()){
            if(paidByUser > item.getPrice()) {
                getChange(paidByUser-item.getPrice());
            }
            m.setVendingMachineState(new DespenseState(m, Code));
        }
    }
    public int getChange(int returnExtraMoney) throws Exception{
        //actual logic should be to return COINs in the dispense tray, but for simplicity i am just returning the amount to be refunded
        System.out.println("Returned the change in the Coin Dispense Tray: " + returnExtraMoney);
        return returnExtraMoney;
    }
    public List<Coin> refundCoin(Machine m) throws Exception{
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        m.setVendingMachineState(new IdleState(m));
        return m.getCoinList();
    }
    public Item DispenseItem(Machine m,int code) throws Exception{
        return new Item();
    }
}
