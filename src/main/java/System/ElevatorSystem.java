package System;

import System.Enumerations.Direction;
import System.Enumerations.ElevatorState;
import System.Model.Others.ElevatorCar;
import System.Model.Others.Floor;
import System.Model.Panel.HallPanel;

import java.util.*;

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

    public void addPendingRequest(Request request){
        pendingRequests.offer(request);
    }

    public boolean isTherePendingRequestsToProcess(){
        return !pendingRequests.isEmpty();
    }

    public Request getPendingRequest(){
        return pendingRequests.poll();
    }

    public void monitoring(){

    }

    @Override
    public void onFloorRequest(Request request) {
        ElevatorCar elevatorCar = dispatchElevator(request);
        if(elevatorCar == null) {
            System.out.println("[ES] Please wait..All elevators are busy");
            addPendingRequest(request);
            return;
        }
        //As elevator car is allocated, move the request to the floor
        building.getFloor(request.getSourceFloor()).addRequest(request);

        if(elevatorCar.getElevatorState() == ElevatorState.IDLE){
            updateElevatorState(elevatorCar, ElevatorState.MOVING, request.getDirection(),
                    request.getDirection());
            Runnable r = () -> elevatorCar.moveElevator(request.getSourceFloor());
            Thread thread = new Thread(r);
            thread.start();
        }else{
            elevatorCar.addRequest(request.getSourceFloor());
        }
    }

    //Find the best elevator
    private ElevatorCar dispatchElevator(Request request){
        List<ElevatorCar> elevatorCars = building.getElevatorCars();
        ElevatorCar selectedElevator = null;
        int bestDistance = (int)1e9;

        for(ElevatorCar ec : elevatorCars){
            int distance;
            if(ec.getElevatorState() == ElevatorState.IDLE){
                int currentFloor = ec.getCurrentFloor();
                //If the elevator is in the same floor as the requested floor and is idle
                //immediately return that car
                if(currentFloor == request.getSourceFloor())   return ec;
                distance = Math.abs(currentFloor - request.getSourceFloor());
            }else if(ec.getDirection() == request.getDirection()){
                if(!ec.isServingRequests()) continue;
                if(request.getDirection() == Direction.UP){
                    if(ec.getCurrentFloor() > request.getSourceFloor())    continue;
                    distance = Math.abs(ec.getCurrentFloor() - request.getSourceFloor());
                }else {
                    if(ec.getCurrentFloor() < request.getSourceFloor())    continue;
                    distance = Math.abs(ec.getCurrentFloor() - request.getSourceFloor());
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

    @Override
    public void onFloorArrival(ElevatorCar elevatorCar) {
        updateElevatorState(elevatorCar, ElevatorState.IDLE, Direction.NULL, null);
        //elevatorCar.updateSourceFloor(request.getSourceFloor());

        System.out.println("[ES] "+elevatorCar);
        elevatorCar.openDoor();
        System.out.println("[ES] "+ elevatorCar.getCurrentFloor() +" Opening Door..-- "+elevatorCar.getId());

        getRequestsInsideElevator(elevatorCar);
        elevatorCar.setServingRequests(true);

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("[ES] "+e.getMessage());
        }

        System.out.println("[ES] "+ elevatorCar.getCurrentFloor() +" Closing Door..-- "+elevatorCar.getId());
        elevatorCar.closeDoor();

        processNextFloor(elevatorCar);
    }

    private void getRequestsInsideElevator(ElevatorCar elevatorCar){
        List<Request> requests = null;
        if(elevatorCar.getServingDirection() == Direction.UP)
            requests = building.getFloor(elevatorCar.getCurrentFloor()).getUpRequests();
        else
            requests = building.getFloor(elevatorCar.getCurrentFloor()).getDownRequests();
        if(requests.isEmpty())    return;
        for(Request re : requests){
            elevatorCar.addRequest(re.getDestinationFloor());
        }
        Floor floor = building.getFloor(elevatorCar.getCurrentFloor());
        floor.removeRequests(requests);
        for(HallPanel hp : floor.getHallPanelList()){
            if(elevatorCar.getDirection() == Direction.UP){
                hp.resetUpButton();
            }else if(elevatorCar.getDirection() == Direction.DOWN){
                hp.resetDownButton();
            }
        }
    }

    private void processNextFloor(ElevatorCar elevatorCar){
        int nextStop = elevatorCar.getNextFloorToStop();
        if(nextStop == -1){
            System.out.println("[EC] All requests processed");
            elevatorCar.resetElevator();
            allocateElevatorToAPendingRequest(elevatorCar);
        }else{
            elevatorCar.moveElevator(nextStop);
        }
    }

    private void allocateElevatorToAPendingRequest(ElevatorCar elevatorCar){
        if(pendingRequests.isEmpty())   return;
        Request request = pendingRequests.poll();
        elevatorCar.moveElevator(request.getSourceFloor());
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

    private void updateElevatorState(ElevatorCar elevatorCar, ElevatorState elevatorState,
                                     Direction direction, Direction servingDirection){
        elevatorCar.setElevatorState(elevatorState);
        elevatorCar.setDirection(direction);
        if(servingDirection != null)
            elevatorCar.setServingDirection(servingDirection);
    }

}
