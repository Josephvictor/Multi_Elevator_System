package System.Model.Others;

import System.Enumerations.DoorState;
import System.Enumerations.ElevatorState;

public class Door {
    private DoorState doorState;

    public Door(){
        this.doorState = DoorState.CLOSE;
    }

    public boolean isOpen(){
        return this.doorState == DoorState.OPEN;
    }

    public void openDoor(){
        this.doorState = DoorState.OPEN;
    }

    public void closeDoor(){
        this.doorState = DoorState.CLOSE;
    }
}
