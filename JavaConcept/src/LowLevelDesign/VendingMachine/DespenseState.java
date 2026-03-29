package LowLevelDesign.VendingMachine;

import java.util.List;

public class DespenseState implements State{
    public DespenseState(){
        System.out.println("Currently Vending machine is in DespenseState");
    }

    public DespenseState(Machine m,int code) throws Exception{
        System.out.println("Currently Vending machine is in DispenseState");
        DispenseItem(m, code);
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
        throw new Exception("not allowed operation");
    }
    public List<Coin> refundCoin(Machine m) throws Exception{
        throw new Exception("not allowed operation");
    }
    public Item DispenseItem(Machine m,int code) throws Exception{
        System.out.println("Product has been dispensed");
        Item item = m.getInventory().getItem(code);
        m.getInventory().updateSoldOutItem(code);
        m.setVendingMachineState(new IdleState(m));
        return item;
    }
}
