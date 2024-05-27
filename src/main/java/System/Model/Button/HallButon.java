package System.Model.Button;

import System.Enumerations.Direction;

public class HallButon extends Button{

    public void pressButton(){
        setStatus(true);
    }

    public void unpressButton(){
        setStatus(false);
    }

    @Override
    public boolean isPressed() {
        return getStatus();
    }
}
