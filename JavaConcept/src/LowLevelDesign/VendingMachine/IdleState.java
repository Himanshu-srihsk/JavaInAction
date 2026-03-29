package LowLevelDesign.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class IdleState implements State{

    public IdleState(){
        System.out.println("Currently Vending machine is in IdleState");
    }

    public IdleState(Machine m){
        m.setCoinList(new ArrayList<>());
    }
    public void clickOnInsertCoinButton(Machine m)  throws Exception{
        System.out.println("Hetev ");
        m.setVendingMachineState(new HashMoneyState());
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
        throw new Exception("not allowed operation");
    }
    public void updateInventory(Machine machine, Item item, int codeNumber) throws Exception {
        machine.getInventory().addItem(item, codeNumber);
    }


}
