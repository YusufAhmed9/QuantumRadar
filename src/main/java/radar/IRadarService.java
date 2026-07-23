package radar;

import car.CarType;

import java.time.LocalDate;
import java.util.Date;

public interface IRadarService {
    public RadarInfo createRadarInfo(String plateNumber, LocalDate date, CarType carType, int speed, boolean seatbeltStatus);
    public boolean validatePlateNumber(String plateNumber);
    public boolean validateSpeed(int speed);
}
