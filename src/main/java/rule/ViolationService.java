package rule;

import car.CarType;
import radar.RadarInfo;
import utils.Pair;

import java.util.ArrayList;

public class ViolationService implements IViolationService {


    private static ViolationService instance;
    private RuleService ruleService;
    private int[] violationCounts = new int[ViolationType.values().length];
    private ArrayList<Pair<String, ArrayList<Violation>>> violations;

    private ViolationService() {
        violations = new ArrayList<>();
        this.ruleService = RuleService.getInstance();
    }

    public static ViolationService getInstance() {
        if (instance == null) instance = new ViolationService();
        return instance;
    }

    private Violation getViolation(String plateNumber, ViolationType violationType) {
        violationCounts[violationType.ordinal()]++;
        return new Violation(violationType.getDescription(), violationType.getFine(), plateNumber, violationType);
    }

    private void addViolationsToVehicle(String plateNumber, ArrayList<Violation> carViolations) {
        boolean added = false;
        for (var item : violations) {
            if (item.key().equals(plateNumber)) {
                added = item.value().addAll(carViolations);
            }
        }
        if (!added) {
            violations.add(new Pair<>(plateNumber, carViolations));
        }
    }

    @Override
    public ArrayList<Violation> addViolation(RadarInfo radarInfo) {
        ArrayList<Violation> carViolations = new ArrayList<>();
        if (!ruleService.validateSpeed(radarInfo)) {
            carViolations.add(getViolation(radarInfo.getPlateNumber(), ViolationType.SPEED));
        }
        if (!ruleService.validateSeatbelt(radarInfo)) {
            carViolations.add(getViolation(radarInfo.getPlateNumber(), ViolationType.SEATBELT));
        }
        if (!carViolations.isEmpty()) {
            addViolationsToVehicle(radarInfo.getPlateNumber(), carViolations);
        }
        return carViolations;
    }

    @Override
    public void printVehicleFines(String plateNumber) {
        System.out.println("Traffic fine for car " + plateNumber);
        for (var item : violations) {
            if (!item.key().equals(plateNumber))
                continue;
            int totalFine = 0;
            for (Violation violation : item.value()) {
                totalFine += violation.getFine();
            }
            System.out.println("Total amount: " + totalFine);
            System.out.println("Violations:");
            for (Violation violation : item.value()) {
                System.out.println("- " + violation);
            }
            return;
        }
        System.out.println("This car has no violations");
    }

    @Override
    public ArrayList<Violation> getVehicleFines(String plateNumber) {
        for (var item : violations) {
            if (!item.key().equals(plateNumber))
                continue;
            return item.value();
        }
        return null;
    }

    @Override
    public Violation removeViolation(String id) {
        for (var item : violations) {
            for (Violation violation : item.value()) {
                if (id.equals(violation.getId())) {
                    violationCounts[violation.getViolationType().ordinal()]--;
                    item.value().remove(violation);
                    if (item.value().isEmpty()) {
                        violations.remove(item);
                    }
                    return violation;
                }
            }
        }
        return null;
    }

    @Override
    public Violation updateViolation(String id, Violation newViolation) {

        for (var item : violations) {
            for (Violation violation : item.value()) {
                if (id.equals(violation.getId())) {
                    if (!newViolation.getViolationType().equals(violation.getViolationType())) {
                        violationCounts[violation.getViolationType().ordinal()]--;
                        violationCounts[newViolation.getViolationType().ordinal()]++;
                    }
                    violation.update(newViolation);
                    return violation;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Pair<String, Integer>> getAllFines() {
        ArrayList<Pair<String, Integer>> fines = new ArrayList<>();
        for (var item : violations) {
            int totalFines = 0;
            for (Violation violation : item.value()) {
                totalFines += violation.getFine();
            }
            fines.add(new Pair<>(item.key(), totalFines));
        }
        return fines;
    }

    @Override
    public ArrayList<Pair<ViolationType, Integer>> getAllViolatedRules() {
        ArrayList<Pair<ViolationType, Integer>> violatedRules = new ArrayList<>();
        for (var violationType : ViolationType.values()) {
            violatedRules.add(new Pair<>(violationType, violationCounts[violationType.ordinal()]));
        }
        return violatedRules;
    }

}
