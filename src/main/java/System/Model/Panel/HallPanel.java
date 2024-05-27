package System.Model.Panel;

import System.Request;
import System.ElevatorRequestListener;
import System.Enumerations.Direction;
import System.Model.Button.HallButon;
import lombok.Data;

@Data
public class HallPanel {
    private final int floorNum;
    private final HallButon up;
    private final HallButon down;
    private ElevatorRequestListener eventListener;


    public HallPanel(int floorNum, ElevatorRequestListener eventListener){
        this.floorNum = floorNum;
        this.up = new HallButon();
        this.down = new HallButon();
        this.eventListener = eventListener;
    }

    public void processRequest(Request request){
        if(request.getDirection() == Direction.UP)
            upRequest(request);
        else downRequest(request);
    }

    public void upRequest(Request request){
        up.pressButton();
        eventListener.onFloorRequest(request);
    }

    public void downRequest(Request request){
        down.pressButton();
        eventListener.onFloorRequest(request);
    }

    public void resetUpButton(){
        up.unpressButton();
    }

    public void resetDownButton(){
        down.unpressButton();
    }
}
