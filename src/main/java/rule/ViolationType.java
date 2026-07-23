package rule;

public enum ViolationType {
    SPEED(RuleConstants.SPEED_FINE, RuleConstants.SPEED_DESCRIPTION),
    SEATBELT(RuleConstants.SEATBELT_FINE, RuleConstants.SEATBELT_DESCRIPTION);
    private final int fine;
    private final String description;

    ViolationType(int fine, String description) {
        this.fine = fine;
        this.description = description;
    }

    public int getFine() {
        return fine;
    }

    public String getDescription() {
        return description;
    }
}
