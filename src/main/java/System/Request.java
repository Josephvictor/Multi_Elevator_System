package System;

import System.Enumerations.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Request {
    private int sourceFloor;
    private int hallPanel;
    private Direction direction;
    private int destinationFloor;
}
