package System.Model.Others;

import System.Enumerations.Direction;
import System.DoorControlListener;
import System.Enumerations.ElevatorState;
import System.Model.Panel.ElevatorPanel;
import lombok.Data;

import java.util.TreeSet;


@Data
public class ElevatorCar {
    private int id;
    private Door door;
    private ElevatorPanel elevatorPanel;
    private Display display;
    private DoorControlListener doorControlListener;
    private ElevatorState elevatorState;
    private Direction direction;
    private int currentFloor;
    private boolean isServingRequests;
    private Direction servingDirection;
    private TreeSet<Integer> requests;

    public ElevatorCar(int id, Door door, ElevatorPanel elevatorPanel,
                       Display display, DoorControlListener doorControlListener) {
        this.id = id;
        this.door = door;
        this.elevatorPanel = elevatorPanel;
        this.display = display;
        this.elevatorState = ElevatorState.IDLE;
        this.direction = null;
        this.currentFloor = 0;
        this.isServingRequests = false;
        this.servingDirection = Direction.NULL;
        requests = new TreeSet<>();
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
        if(currentFloor != goToFloor){
            setElevatorState(ElevatorState.MOVING);
            int currentFloor = getCurrentFloor();
            if(currentFloor > goToFloor){
                setDirection(Direction.DOWN);
                moveDown(goToFloor);
            }
            else if(currentFloor < goToFloor){
                setDirection(Direction.UP);
                moveUp(goToFloor);
            }
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

    public void addRequest(int floorNum){
        requests.add(floorNum);
    }

    public int getNextFloorToStop(){
        int nextStop = -1;
        if(requests.isEmpty())  return nextStop;
        if(servingDirection == Direction.UP){
            nextStop = requests.first();
            requests.pollFirst();
        }
        else if (servingDirection == Direction.DOWN){
            nextStop =  requests.last();
            requests.pollLast();
        }
        return nextStop;
    }

    public void resetElevator(){
        elevatorState = ElevatorState.IDLE;
        direction = Direction.NULL;
        servingDirection = Direction.NULL;
        isServingRequests = false;
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
