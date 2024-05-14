package System.Model.Panel;

import System.Model.Button.DoorButton;
import System.Model.Button.ElevatorButton;

import java.util.List;

public class ElevatorPanel {
    private List<ElevatorButton> floorButtons;
    private DoorButton openButton;
    private DoorButton closeButton;

    public ElevatorPanel(){
        this.openButton = new DoorButton();
        this.closeButton = new DoorButton();
    }

    public void addFloorButtons(int numOfFloorButtons){
        for(int idx = 0; idx <= numOfFloorButtons; idx++){
            ElevatorButton elevatorButton = new ElevatorButton(idx);
            floorButtons.add(elevatorButton);
        }
    }
}
