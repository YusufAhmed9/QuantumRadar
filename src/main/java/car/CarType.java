package car;

public enum CarType {
    PRIVATE(CarConstraints.PRIVATE_CAR_SPEED_LIMIT),
    TRUCK(CarConstraints.TRUCK_SPEED_LIMIT),
    BUS(CarConstraints.BUS_SPEED_LIMIT);
    private final int speedLimit;

    CarType(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }
}
