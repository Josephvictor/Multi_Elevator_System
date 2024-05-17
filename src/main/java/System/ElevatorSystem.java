package System;

import System.Enumerations.Direction;
import System.Enumerations.ElevatorState;
import System.Model.Others.ElevatorCar;
import System.Model.Others.Floor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorSystem implements ElevatorRequestListener, DoorControlListener{
    private final Building building;
    private final Queue<Request> pendingRequests;

    private ElevatorSystem(){
        this.building = Building.getInstance();
        this.pendingRequests = new LinkedList<>();
    }
    private static ElevatorSystem elevatorSystem = null;

    public static ElevatorSystem getInstance(){
        if(elevatorSystem == null)  elevatorSystem = new ElevatorSystem();
        return elevatorSystem;
    }

    public void addFloors(int floors){
        building.addFloors(floors);
    }

    public void addElevators(int elevators){
        building.addElevatorCars(elevators);
    }

    public void addPendingRequest(int floor, Direction direction){
        pendingRequests.offer(new Request(floor, direction, -1));
    }

    public Request getPendingRequest(){
        return pendingRequests.poll();
    }

    public void monitoring(){

    }

    @Override
    public void onFloorRequest(int floor, Direction direction) {
        ElevatorCar elevatorCar = dispatchElevator(floor, direction);
        if(elevatorCar == null) {
            System.out.println("[ES] Please wait..All elevators are busy");
            addPendingRequest(floor, direction);
        }

        if(elevatorCar.getElevatorState() == ElevatorState.IDLE){
            if(direction == Direction.UP) updateElevatorState(elevatorCar, ElevatorState.UP);
            else updateElevatorState(elevatorCar, ElevatorState.DOWN);
            Runnable r = () -> elevatorCar.moveElevator(floor);
            Thread thread = new Thread(r);
            thread.start();
        }else{

        }
    }

    private ElevatorCar dispatchElevator(int floor, Direction direction){
        List<ElevatorCar> elevatorCars = building.getElevatorCars();
        ElevatorCar selectedElevator = null;
        int bestDistance = (int)1e9;
        for(ElevatorCar ec : elevatorCars){
            int distance;
            if(ec.getElevatorState() == ElevatorState.IDLE){
                int currentFloor = ec.getCurrentFloor();
                //If the elevator is in the same floor as the requested floor and is idle
                //immediately return that car
                if(currentFloor == floor)   return ec;
                distance = Math.abs(currentFloor - floor);
            }else if(ec.getElevatorState().name().equalsIgnoreCase(direction.name())){
                if(ec.getDestinationFloor() == -1)  continue;
                if(direction == Direction.UP){
                    if(ec.getCurrentFloor() > floor)    continue;
                    distance = Math.abs(ec.getCurrentFloor() - floor);
                }else {
                    if(ec.getCurrentFloor() < floor)    continue;
                    distance = Math.abs(ec.getCurrentFloor() - floor);
                }
            }else{
                continue;
            }

            if(distance < bestDistance){
                selectedElevator = ec;
                bestDistance = distance;
            }
        }
        return selectedElevator;
    }

    private void controlDoor(ElevatorCar elevatorCar){
        elevatorCar.setElevatorState(ElevatorState.IDLE);
        openDoor(elevatorCar);
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        closeDoor(elevatorCar);
    }

    private void openDoor(ElevatorCar elevatorCar){
        elevatorCar.openDoor();
    }

    private void closeDoor(ElevatorCar elevatorCar){
        elevatorCar.closeDoor();
    }

    public Floor getFloor(int floor){
        return building.getFloor(floor);
    }

    private void updateElevatorState(ElevatorCar elevatorCar, ElevatorState elevatorState){
        elevatorCar.setElevatorState(elevatorState);
    }


    @Override
    public void onFloorArrival(ElevatorCar elevatorCar) {
        updateElevatorState(elevatorCar, ElevatorState.IDLE);
        System.out.println("[ES] "+elevatorCar);
        elevatorCar.openDoor();
        System.out.println("[ES] "+ elevatorCar.getCurrentFloor() +" Opening Door..-- "+elevatorCar.getId());
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("[ES] "+e.getMessage());
        }
        System.out.println("[ES] "+ elevatorCar.getCurrentFloor() +" Closing Door..-- "+elevatorCar.getId());
        elevatorCar.closeDoor();
    }
}
