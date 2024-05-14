package System.Model.Button;

import System.Enumerations.Direction;

public class HallButon extends Button{
    private final Direction direction;

    public HallButon(Direction direction){
        this.direction = direction;
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
