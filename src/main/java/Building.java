import Model.Others.ElevatorCar;
import Model.Others.Floor;

import java.util.List;

public class Building {
    private List<Floor> floorList;
    private List<ElevatorCar> elevatorCarList;

    private static Building building = null;

    public static Building getInstance(){
        if(building == null)    building = new Building();
        return building;
    }

    private Building(){}
}
