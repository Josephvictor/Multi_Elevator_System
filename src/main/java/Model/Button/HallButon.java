package Model.Button;

import Enumerations.Direction;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HallButon extends Button{
    private Direction direction;

    @Override
    public boolean isPressed() {
        return false;
    }
}
