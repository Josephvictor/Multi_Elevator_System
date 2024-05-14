package System;

import System.Model.Others.Display;
import System.Model.Others.Door;
import System.Model.Others.ElevatorCar;
import System.Model.Others.Floor;
import System.Model.Panel.ElevatorPanel;
import System.Model.Panel.HallPanel;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Building {
    private int totalFloors;
    private int totalElevators;
    private List<Floor> floors;
    private List<ElevatorCar> elevatorCars;

    private Building(){
        floors = new ArrayList<>();
        elevatorCars = new ArrayList<>();
    }

    private static Building building = null;
    public static Building getInstance(){
        if(building == null)    building = new Building();
        return building;
    }

    public void addFloors(int numOfFloors){
        for(int idx = totalFloors; idx <= numOfFloors; idx++){
            Floor floor = new Floor(totalFloors++);
            floors.add(floor);
        }
    }

    public void addHallPanelAndDisplayForEachFloor(){
        for(int i = 0; i <= totalFloors; i++){
            for(int j = 1; j <= totalElevators; j++){
                Floor floor = floors.get(i);
                floor.addHallPanel(new HallPanel(j, (ElevatorRequestListener) floor));
                floor.addDisplay(elevatorCars.get(j).getDisplay());
            }
        }
    }

    public void addElevatorCars(int numOfElevatorCars){
        for(int idx = totalElevators; idx < numOfElevatorCars; idx++){

            Door door = new Door();
            ElevatorPanel elevatorPanel = new ElevatorPanel();
            elevatorPanel.addFloorButtons(totalFloors);
            Display display = new Display();

            ElevatorCar elevatorCar = new ElevatorCar(++totalElevators, door,
                    elevatorPanel, display);

        }
        addHallPanelAndDisplayForEachFloor();
    }

    public Floor getFloor(int floorNum){
        return floors.get(floorNum);
    }


}
