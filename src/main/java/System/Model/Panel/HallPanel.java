package System.Model.Panel;

import System.ElevatorRequestListener;
import System.Enumerations.Direction;
import System.Model.Button.HallButon;
import lombok.Data;

@Data
public class HallPanel {
    private final int floorNum;
    private final HallButon up;
    private final HallButon down;
    private boolean pressedDown;
    private boolean pressedUp;
    private ElevatorRequestListener eventListener;


    public HallPanel(int floorNum, ElevatorRequestListener eventListener){
        this.floorNum = floorNum;
        this.up = new HallButon(Direction.UP);
        this.down = new HallButon(Direction.DOWN);
        this.eventListener = eventListener;

    }

    public void unpressDown(){
        this.pressedDown = false;
    }

    public void unpressUp(){
        this.pressedUp = false;
    }

    public boolean upRequest(){

        if(isPressedUp())   return true;
        up.pressButton();
        eventListener.onFloorRequest(Direction.UP);
        return true;
    }

    public void downRequest(){

    }
}