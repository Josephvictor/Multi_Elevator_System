package Model.Others;

import Enumerations.DoorState;

public class Door {
    private DoorState doorState;

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
