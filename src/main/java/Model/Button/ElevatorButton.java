package Model.Button;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ElevatorButton extends Button{
    private int destinationFLoor;


    @Override
    public boolean isPressed() {
        return false;
    }
}
