package LowLevelDesign.Elevator;

import java.util.List;

public class Floor {
    int floorNumber;
    ExternalButton externalButton;
    Floor(int floorNumber){
        this.floorNumber = floorNumber;
        externalButton = new ExternalButton();
    }
    public void pressButton(Direction direction){
        externalButton.submitRequest(floorNumber, direction);
    }
}
