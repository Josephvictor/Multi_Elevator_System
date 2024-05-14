package System;

import System.Model.Others.Floor;

public class App {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();
        elevatorSystem.addFloors(3);
        elevatorSystem.addElevators(3);

        Building building = Building.getInstance();
        Floor floor = building.getFloor(0);

        System.out.println(floor.getHallPanel(0).upRequest());
        System.out.println(floor.getHallPanel(1).upRequest());
        System.out.println(floor.getHallPanel(2).upRequest());
        System.out.println(floor.getHallPanel(3).upRequest());
    }
}
