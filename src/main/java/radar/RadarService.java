package radar;

import car.CarType;

import java.time.LocalDate;
import java.util.Date;

public class RadarService implements IRadarService {
    private static RadarService instance;

    private RadarService() {

    }

    public static RadarService getInstance() {
        if (instance == null) instance = new RadarService();
        return instance;
    }

    @Override
    public RadarInfo createRadarInfo(String plateNumber, LocalDate date, CarType carType, int speed, boolean seatbeltStatus)  {
        if (!validatePlateNumber(plateNumber))
            return null;
        if (!validateSpeed(speed))
            return null;
        return new RadarInfo(plateNumber, date, carType, speed, seatbeltStatus);
    }

    @Override
    public boolean validatePlateNumber(String plateNumber) {
        return !plateNumber.isEmpty();
    }

    @Override
    public boolean validateSpeed(int speed) {
        return speed >= 0;
    }
}
