package dev.riyenas.osam.domain.log;

public enum ReturnState {
    PASS("정상반납", "bg-success"),
    NON_PASS("미반납", "bg-danger"),
    TIME_DELAY("지연반납", "bg-warning"),
    WEIGHT_FAULT("무게이상", "bg-warning");

    ReturnState(String state, String icon) {
        this.state = state;
        this.icon = icon;
    }

    private final String state;
    private final String icon;

    public String getState() {
        return state;
    }

    public String getIcon() {
        return icon;
    }
}
