package Model.Others;

import Enumerations.ElevatorState;
import Model.Panel.ElevatorPanel;
import lombok.Data;

@Data
public class ElevatorCar {
    private int id;
    private ElevatorState elevatorState;
    private Door door;
    private ElevatorPanel elevatorPanel;
    private Display display;
    private int currentFloor;

    public void openDoor(){
        this.door.openDoor();
    }

    public void closeDoor(){
        this.door.closeDoor();
    }

    public void moveElevator(){

    }

    public void stopElevator(){
        this.elevatorState = ElevatorState.IDLE;
    }
}
