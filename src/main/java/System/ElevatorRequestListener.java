package System;

import System.Enumerations.Direction;

public interface ElevatorRequestListener {
    void onFloorRequest(Direction direction);
}
