package radar;

import car.CarType;

import java.time.LocalDate;
import java.util.Date;

public class RadarInfo {
    private String plateNumber;
    private LocalDate date;
    private CarType carType;
    private int speed;
    private boolean seatbeltStatus;

    public RadarInfo(String plateNumber, LocalDate date, CarType carType, int speed, boolean seatbeltStatus) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speed = speed;
        this.seatbeltStatus = seatbeltStatus;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getSeatbeltStatus() {
        return seatbeltStatus;
    }

    public void setSeatbeltStatus(boolean seatbeltStatus) {
        this.seatbeltStatus = seatbeltStatus;
    }
}
