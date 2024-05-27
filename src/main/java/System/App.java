package System;

import System.Enumerations.Direction;
import System.Model.Others.Floor;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();
        elevatorSystem.addFloors(3);
        elevatorSystem.addElevators(3);

        //Create some requests
        List<Request> list = new ArrayList<>();
        Request request1 = new Request(3, 0, Direction.UP, 5);
        Request request2 = new Request(3, 1,  Direction.UP, 4);
        //Request request3 = new Request(1, 2, Direction.UP, 5);
        list.add(request1);
        list.add(request2);
        //list.add(request3);

        for(Request request : list){
            Floor floor = elevatorSystem.getFloor(request.getSourceFloor());
            floor.getHallPanel(request.getHallPanel()).processRequest(request);
        }
    }
}
