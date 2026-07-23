package rule;

import radar.RadarInfo;

public interface IRuleService {

    public boolean validateSpeed(RadarInfo item);
    public boolean validateSeatbelt(RadarInfo item);

}
