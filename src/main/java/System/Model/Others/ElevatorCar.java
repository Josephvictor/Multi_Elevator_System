package System.Model.Others;

import System.DoorControlListener;
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
    private int sourceFloor;
    private int destinationFloor;
    private DoorControlListener doorControlListener;

    public ElevatorCar(int id, Door door, ElevatorPanel elevatorPanel,
                       Display display, DoorControlListener doorControlListener) {
        this.id = id;
        this.door = door;
        this.elevatorPanel = elevatorPanel;
        this.display = display;
        this.elevatorState = ElevatorState.IDLE;
        this.currentFloor = 0;
        this.destinationFloor = -1;
        this.sourceFloor = -1;
        this.doorControlListener = doorControlListener;
    }

    public void openDoor(){
        if(this.elevatorState == ElevatorState.IDLE)
            this.door.openDoor();
        else{
            System.err.println("[Car][door] Invalid state");
        }
    }

    public void closeDoor(){
        if(this.elevatorState == ElevatorState.IDLE)
            this.door.closeDoor();
        else{
            System.err.println("[Car][door] Invalid state");
        }
    }


    public void moveElevator(int goToFloor){
        int currentFloor = getCurrentFloor();

        if(currentFloor > goToFloor){
            moveDown(goToFloor);
        }
        else if(currentFloor < goToFloor){
            moveUp(goToFloor);
        }

        doorControlListener.onFloorArrival(this);
    }

    public void moveUp(int goToFloor){
        for(int i = currentFloor; i < goToFloor; i++){
            setCurrentFloor(++currentFloor);
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("[EC] Now at floor :"+ currentFloor + " ~~ Elevator "+id);
        }
    }

    public void moveDown(int goToFloor){
        for(int i = currentFloor; i > goToFloor; i--){
            setCurrentFloor(--currentFloor);
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("[EC] Now at floor :"+ currentFloor + " ~~ Elevator "+id);
        }
    }

    public void stopElevator(){
        this.elevatorState = ElevatorState.IDLE;
    }

    @Override
    public String toString() {
        return "ElevatorCar{" +
                "id=" + id +
                ", elevatorState=" + elevatorState +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
