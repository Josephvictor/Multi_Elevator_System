package System;

import System.Model.Others.Floor;

public class App {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();
        elevatorSystem.addFloors(3);
        elevatorSystem.addElevators(3);

        Floor floor3 = elevatorSystem.getFloor(3);
        floor3.getHallPanel(0).upRequest();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        Floor floor1 = elevatorSystem.getFloor(1);
        floor1.getHallPanel(1).upRequest();


    }
}
