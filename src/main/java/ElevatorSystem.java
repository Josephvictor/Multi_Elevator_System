import Model.Others.ElevatorCar;

public class ElevatorSystem {
    private Building building;

    private ElevatorSystem(){}
    private static ElevatorSystem elevatorSystem = null;

    public static ElevatorSystem getInstance(){
        if(elevatorSystem == null)  elevatorSystem = new ElevatorSystem();
        return elevatorSystem;
    }

    public void monitoring(){}

    public ElevatorCar selectBestElevator(){
        return null;
    }
}
