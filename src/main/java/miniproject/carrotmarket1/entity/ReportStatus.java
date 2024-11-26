package miniproject.carrotmarket1.entity;

public enum ReportStatus {
    PENDING("Pending"),
    RESOLVED("Resolved");

    private final String displayName;

    ReportStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
