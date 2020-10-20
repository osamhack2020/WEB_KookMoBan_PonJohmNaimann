package dev.riyenas.osam.domain.log;

public enum ReturnState {
    PASS("정상반납"),
    TIME_DELAY("지연반납"),
    WEIGHT_FAULT("무게이상");

    ReturnState(String state) {
        this.state = state;
    }

    private final String state;

    public String getState() {
        return state;
    }
}
