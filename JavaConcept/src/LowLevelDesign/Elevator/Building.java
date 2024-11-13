package LowLevelDesign.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors;

    public List<Floor> getFloors() {
        return new ArrayList<>(floors);
    }

    public Building(List<Floor> floors) {
        this.floors = floors;
    }
}
