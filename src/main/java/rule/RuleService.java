package rule;

import car.CarType;
import radar.RadarInfo;

public class RuleService implements IRuleService {
    private static RuleService instance;

    private RuleService() {
    }

    public static RuleService getInstance() {
        if (instance == null) instance = new RuleService();
        return instance;
    }

    @Override
    public boolean validateSpeed(RadarInfo item) {
        return item.getSpeed() <= item.getCarType().getSpeedLimit();
    }

    @Override
    public boolean validateSeatbelt(RadarInfo item) {
        return item.getSeatbeltStatus();
    }
}
