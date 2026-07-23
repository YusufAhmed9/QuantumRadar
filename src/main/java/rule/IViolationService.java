package rule;

import radar.RadarInfo;
import utils.Pair;

import java.util.ArrayList;

public interface IViolationService {
    public ArrayList<Violation> addViolation(RadarInfo radarInfo);

    public void printVehicleFines(String plateNumber);

    public Violation removeViolation(String id);

    public Violation updateViolation(String id, Violation newViolation);

    public ArrayList<Pair<String, Integer>> getAllFines();

    public ArrayList<Pair<ViolationType, Integer>> getAllViolatedRules();
}
