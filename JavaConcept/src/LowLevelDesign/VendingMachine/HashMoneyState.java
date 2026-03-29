package LowLevelDesign.VendingMachine;

import java.util.List;

public class HashMoneyState implements State{
    public HashMoneyState(){
        System.out.println("Currently Vending machine is in HashMoneyState");
    }

    public void clickOnInsertCoinButton(Machine m) throws Exception{
        throw new Exception("not allowed operation");
    }
    public void insertCoin(Machine m,Coin coin) throws Exception{
        m.getCoinList().add(coin);
    }
    public void clickOnStartProductSelectionButton(Machine m)  throws Exception{
        m.setVendingMachineState(new SelectionState());
    }
    public void SelectItem(Machine m,int Code) throws Exception{
        throw new Exception("not allowed operation");
    }
    public List<Coin> refundCoin(Machine m) throws Exception{
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        m.setVendingMachineState(new IdleState(m));
        return m.getCoinList();
    }
    public Item DispenseItem(Machine m,int code) throws Exception{
        throw new Exception("not allowed operation");
    }
}
