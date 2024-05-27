package System;

import System.Model.Others.Display;
import System.Model.Others.Door;
import System.Model.Others.ElevatorCar;
import System.Model.Others.Floor;
import System.Model.Panel.ElevatorPanel;
import System.Model.Panel.HallPanel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Building {
    private int totalFloors;
    private int totalElevators;
    private final List<Floor> floors;
    private final List<ElevatorCar> elevatorCars;

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
        for(int idx = 0; idx <= numOfFloors; idx++){
            Floor floor = new Floor(idx, /*ERL*/ElevatorSystem.getInstance());
            totalFloors++;
            floors.add(floor);
        }
    }

    public void addHallPanelAndDisplayForEachFloor(){

        for(int i = 0; i < totalFloors; i++){
            Floor floor = floors.get(i);
            for(int j = 0; j < totalElevators; j++){
                floor.addHallPanel(new HallPanel(i, floor));
                floor.addDisplay(elevatorCars.get(j).getDisplay());
            }
        }
    }

    public void addElevatorCars(int numOfElevatorCars){
        for(int idx = 1; idx <= numOfElevatorCars; idx++){

            Door door = new Door();
            ElevatorPanel elevatorPanel = new ElevatorPanel();
            elevatorPanel.addFloorButtons(totalFloors);
            Display display = new Display();

            ElevatorCar elevatorCar = new ElevatorCar(idx, door,
                    elevatorPanel, display, ElevatorSystem.getInstance());

            totalElevators++;
            elevatorCars.add(elevatorCar);
        }
        addHallPanelAndDisplayForEachFloor();
    }

    public Floor getFloor(int floorNum){
        return floors.get(floorNum);
    }


}
