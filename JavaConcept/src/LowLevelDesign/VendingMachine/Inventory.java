package LowLevelDesign.VendingMachine;

public class Inventory{
    ItemShelf[] inventory;
    Inventory(int ItemCount){
        inventory = new ItemShelf[ItemCount];
        initialEmptyInventory();
    }
    public ItemShelf[] getItems(){
        return inventory;
    }
    public void setItems(ItemShelf[] inventory){
        this.inventory=inventory;
    }
    public void initialEmptyInventory() {
        int StartCode=101;
        for(int i=0;i<inventory.length;i++){
            ItemShelf itemShelf=new ItemShelf();

            itemShelf.setCode(StartCode);
            itemShelf.setSoldOut(true);
            inventory[i]=itemShelf;
            StartCode++;

        }
    }
    public void addItem(Item item, int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if(itemShelf.getCode() == codeNumber){
                if(itemShelf.isSoldOut()){
                    itemShelf.setItem(item);
                    itemShelf.setSoldOut(false);
                }else{
                    throw new Exception("already item is present, you can not add item here");
                }
            }
        }
    }

    public Item getItem(int codeNumber) throws Exception {

        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.code == codeNumber) {
                if (itemShelf.isSoldOut()) {
                    throw new Exception("item already sold out");
                } else {

                    return itemShelf.item;
                }
            }
        }
        throw new Exception("Invalid Code");
    }


    public void updateSoldOutItem(int codeNumber){
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.code == codeNumber) {
                itemShelf.setSoldOut(true);
            }
        }
    }

}
