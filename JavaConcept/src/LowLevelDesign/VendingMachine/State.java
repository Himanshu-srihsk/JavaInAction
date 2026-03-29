package LowLevelDesign.VendingMachine;

import java.util.List;

public interface State {
    public void clickOnInsertCoinButton(Machine m)  throws Exception;
    public void insertCoin(Machine m,Coin coin) throws Exception;
    public  void clickOnStartProductSelectionButton(Machine m)  throws Exception;

    public void SelectItem(Machine m,int Code) throws Exception;
    public List<Coin> refundCoin(Machine m) throws Exception;
    public Item DispenseItem(Machine m,int code) throws Exception;
}
