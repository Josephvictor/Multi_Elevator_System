package System;

import System.Enumerations.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private int sourceFloor;
    private Direction direction;
    private int destinationFloor;


}
