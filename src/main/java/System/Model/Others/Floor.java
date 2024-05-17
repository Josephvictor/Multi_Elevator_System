package System.Model.Others;

import System.ElevatorRequestListener;
import System.Enumerations.Direction;
import System.Model.Panel.HallPanel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Floor implements ElevatorRequestListener {
    private int floorNumber;
    private final List<HallPanel> hallPanelList;
    private final List<Display> displayList;
    private boolean upRequested;
    private boolean downRequested;

    private ElevatorRequestListener listener;


    public Floor(int floorNumber, ElevatorRequestListener listener){
        this.floorNumber = floorNumber;
        hallPanelList = new ArrayList<>();
        displayList = new ArrayList<>();
        this.listener = listener;
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
    public void onFloorRequest(int floor, Direction direction) {
        if(direction == Direction.UP){
            if(upRequested){
                System.out.println("Already requested");
                return;
            }
            upRequested = true;
            listener.onFloorRequest(floor, Direction.UP);
        }else if(direction == Direction.DOWN){
            if(downRequested)   return;
            downRequested = true;
            listener.onFloorRequest(floor, Direction.DOWN);
        }else{
            System.err.println("Invalid request");
        }
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNumber=" + floorNumber +
                '}';
    }
}
