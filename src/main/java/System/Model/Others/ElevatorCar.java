package System.Model.Others;

import System.Enumerations.ElevatorState;
import System.Model.Panel.ElevatorPanel;
import lombok.Data;

@Data
public class ElevatorCar {
    private int id;
    private ElevatorState elevatorState;
    private Door door;
    private ElevatorPanel elevatorPanel;
    private Display display;
    private int currentFloor;

    public ElevatorCar(int id){
        this.id = id;
        this.elevatorState = ElevatorState.IDLE;
    }

    public ElevatorCar(int id, Door door, ElevatorPanel elevatorPanel,
                       Display display) {
        this.id = id;
        this.door = door;
        this.elevatorPanel = elevatorPanel;
        this.display = display;
        this.elevatorState = ElevatorState.IDLE;
        this.currentFloor = 0;
    }


    public void setDoor(Door door){
        this.door = door;
    }

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
