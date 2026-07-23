import car.CarConstraints;
import car.CarType;
import radar.RadarInfo;
import radar.RadarService;
import rule.ViolationService;

import java.time.LocalDate;

public class RadarSystemTester {
    private RadarService radarService;
    private ViolationService violationService;

    public RadarSystemTester() {
        this.radarService = RadarService.getInstance();
        this.violationService = ViolationService.getInstance();
    }

    public void testCreatingRadarEntities() {
        System.out.println("Testing creating radar captured entities");
        RadarInfo radarInfo1 = radarService.createRadarInfo("ABC123", LocalDate.now(), CarType.PRIVATE, 100, false);
        RadarInfo radarInfo2 = radarService.createRadarInfo("XYZ456", LocalDate.now(), CarType.BUS, 50, true);
        RadarInfo radarInfo3 = radarService.createRadarInfo("ABC123", LocalDate.now(), CarType.TRUCK, -10, false);
        if (radarInfo1 != null && radarInfo2 != null && radarInfo3 == null) {
            System.out.println("Entities created successfully");
            return;
        }
        System.out.println("Error @RadarService.createRadarInfo");
    }

    public void testRuleViolation() {
        System.out.println("Testing speed violations");
        RadarInfo car = radarService.createRadarInfo("ABC123", LocalDate.now(), CarType.PRIVATE, CarConstraints.PRIVATE_CAR_SPEED_LIMIT + 1, true);
        violationService.addViolation(car);
        violationService.printVehicleFines(car.getPlateNumber());
        RadarInfo bus = radarService.createRadarInfo("XYZ456", LocalDate.now(), CarType.BUS, CarConstraints.BUS_SPEED_LIMIT + 1, true);
        violationService.addViolation(bus);
        violationService.printVehicleFines(bus.getPlateNumber());
        RadarInfo truck = radarService.createRadarInfo("HIJ789", LocalDate.now(), CarType.TRUCK, CarConstraints.TRUCK_SPEED_LIMIT + 1, true);
        violationService.addViolation(truck);
        violationService.printVehicleFines(truck.getPlateNumber());

        System.out.println("Testing seatbelt violations");
        RadarInfo car1 = radarService.createRadarInfo("X", LocalDate.now(), CarType.PRIVATE, CarConstraints.PRIVATE_CAR_SPEED_LIMIT, false);
        violationService.addViolation(car1);
        violationService.printVehicleFines(car1.getPlateNumber());
        RadarInfo bus1 = radarService.createRadarInfo("y", LocalDate.now(), CarType.BUS, CarConstraints.BUS_SPEED_LIMIT, false);
        violationService.addViolation(bus1);
        violationService.printVehicleFines(bus1.getPlateNumber());
        RadarInfo truck1 = radarService.createRadarInfo("z", LocalDate.now(), CarType.TRUCK, CarConstraints.TRUCK_SPEED_LIMIT, false);
        violationService.addViolation(truck1);
        violationService.printVehicleFines(truck1.getPlateNumber());

        System.out.println("Testing both speed and seatbelt violations");
        RadarInfo car2 = radarService.createRadarInfo("a", LocalDate.now(), CarType.PRIVATE, CarConstraints.PRIVATE_CAR_SPEED_LIMIT + 1, false);
        violationService.addViolation(car2);
        violationService.printVehicleFines(car2.getPlateNumber());
        RadarInfo bus2 = radarService.createRadarInfo("b", LocalDate.now(), CarType.BUS, CarConstraints.BUS_SPEED_LIMIT + 1, false);
        violationService.addViolation(bus2);
        violationService.printVehicleFines(bus2.getPlateNumber());
        RadarInfo truck2 = radarService.createRadarInfo("c", LocalDate.now(), CarType.TRUCK, CarConstraints.TRUCK_SPEED_LIMIT + 1, false);
        violationService.addViolation(truck2);
        violationService.printVehicleFines(truck2.getPlateNumber());

        System.out.println("Total fines: ");
        if(violationService.getAllFines().size() != 9){
            System.out.println("Not all car fines added");
            return;
        }

        for (var item : violationService.getAllFines()) {
            System.out.println(item.key() + " : " + item.value());
        }

        System.out.println("Violation rule counts: ");
        for (var item : violationService.getAllViolatedRules()) {
            System.out.println(item.key() + " : " + item.value());
        }
    }


    public void testUnviolatedRule() {
        System.out.println("Testing good citizens");
        RadarInfo car = radarService.createRadarInfo("ABC123", LocalDate.now(), CarType.PRIVATE, CarConstraints.PRIVATE_CAR_SPEED_LIMIT, true);
        violationService.addViolation(car);
        violationService.printVehicleFines(car.getPlateNumber());
        RadarInfo bus = radarService.createRadarInfo("XYZ456", LocalDate.now(), CarType.BUS, CarConstraints.BUS_SPEED_LIMIT, true);
        violationService.addViolation(bus);
        violationService.printVehicleFines(bus.getPlateNumber());
        RadarInfo truck = radarService.createRadarInfo("HIJ789", LocalDate.now(), CarType.TRUCK, CarConstraints.TRUCK_SPEED_LIMIT, true);
        violationService.addViolation(truck);
        violationService.printVehicleFines(truck.getPlateNumber());

        System.out.println("Total fines: ");
        for (var item : violationService.getAllFines()) {
            System.out.println(item.key() + " : " + item.value());
        }

        System.out.println("Violation rule counts: ");
        for (var item : violationService.getAllViolatedRules()) {
            System.out.println(item.key() + " : " + item.value());
        }
    }
    public void runAllTests(){
        testCreatingRadarEntities();
        System.out.println("=======================");
        testUnviolatedRule();
        System.out.println("=======================");
        testRuleViolation();
    }
}
