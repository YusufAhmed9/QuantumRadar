package rule;

import java.util.UUID;

public class Violation {
    private String id;
    private String description;
    private int fine;
    private String plateNumber;

    public Violation(String description, int fine, String plateNumber) {
        this.description = description;
        this.fine = fine;
        this.plateNumber = plateNumber;
        this.id = UUID.randomUUID().toString();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getId() {
        return id;
    }

    public Violation update(Violation violation) {
        setDescription(violation.getDescription());
        setFine(violation.getFine());
        setPlateNumber(violation.getPlateNumber());
        return this;
    }

    @Override
    public String toString() {
        return description + " : " + fine;
    }
}
