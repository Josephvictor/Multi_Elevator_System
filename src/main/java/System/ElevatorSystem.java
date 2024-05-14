package System;

import System.Model.Others.ElevatorCar;

public class ElevatorSystem {
    private final Building building;

    private ElevatorSystem(){
        this.building = Building.getInstance();
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

    public void callElevator(int floor){

    }

    public void monitoring(){}

    public ElevatorCar selectBestElevator(){
        return null;
    }
}
