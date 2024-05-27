package System.Model.Others;

import System.Request;
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
    private List<Request> upRequests;
    private List<Request> downRequests;
    private ElevatorRequestListener listener;


    public Floor(int floorNumber, ElevatorRequestListener listener){
        this.floorNumber = floorNumber;
        this.hallPanelList = new ArrayList<>();
        this.displayList = new ArrayList<>();
        this.upRequests = new ArrayList<>();
        this.downRequests = new ArrayList<>();
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

    public void addRequest(Request request){
        if(request.getDirection() == Direction.UP)  upRequests.add(request);
        else downRequests.add(request);
    }

    public void removeRequests(List<Request> requests){
        if(requests.get(0).getDirection() == Direction.UP)
            upRequests.removeAll(requests);
        else
            downRequests.removeAll(requests);
    }

    public boolean isTop(){
        return false;
    }

    public boolean isBottom(){
        return false;
    }

    @Override
    public void onFloorRequest(Request request) {
        addRequest(request);
        Direction direction = request.getDirection();
        if(direction == Direction.UP){
            if(upRequested){
                System.out.println("[floor] Already requested");
                return;
            }
            upRequested = true;
        }else if(direction == Direction.DOWN){
            if(downRequested){
                System.out.println("[floor] Already requested");
                return;
            }
            downRequested = true;
        }else{
            System.err.println("[floor] Invalid request");
            return;
        }
        listener.onFloorRequest(request);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNumber=" + floorNumber +
                '}';
    }
}
