package System.Model.Others;

import System.ElevatorRequestListener;
import System.Enumerations.Direction;
import System.Model.Panel.HallPanel;
import lombok.Data;

import java.util.List;

@Data
public class Floor implements ElevatorRequestListener {
    private int floorNumber;
    private List<HallPanel> hallPanelList;
    private List<Display> displayList;


    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
    }

    public void addHallPanel(HallPanel hallPanel){
        hallPanelList.add(hallPanel);
    }

    public void addDisplay(Display ds){
        displayList.add(ds);
    }

    public HallPanel getHallPanel(int num){
        return hallPanelList.get(num);
    }

    public boolean isTop(){
        return false;
    }

    public boolean isBottom(){
        return false;
    }

    @Override
    public void onFloorRequest(Direction direction) {
        System.out.println("Elevator requested");
    }
}
