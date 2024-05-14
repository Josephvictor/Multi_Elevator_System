package System.Model.Button;


public class ElevatorButton extends Button{
    private final int floorNum;

    public ElevatorButton(int floorNum){
        this.floorNum = floorNum;
    }

    public int getFloorNum(){
        return floorNum;
    }

    public void pressButton(){
        super.setStatus(true);
    }

    public void unpressButton(){
        super.setStatus(false);
    }

    @Override
    public boolean isPressed() {
        return super.getStatus();
    }
}
