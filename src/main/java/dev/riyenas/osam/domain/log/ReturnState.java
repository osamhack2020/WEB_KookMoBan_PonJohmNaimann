package dev.riyenas.osam.domain.log;

public enum ReturnState {
    PASS("정상반납", "bg-success", 4L),
    NON_PASS("미반납", "bg-danger", 1L),
    TIME_DELAY("지연반납", "bg-warning", 2L),
    WEIGHT_FAULT("무게이상", "bg-yellow", 3L);

    ReturnState(String state, String icon, Long order) {
        this.state = state;
        this.icon = icon;
        this.order = order;
    }

    private final String state;
    private final String icon;
    private final Long order;

    public String getState() {
        return state;
    }

    public String getIcon() {
        return icon;
    }

    public Long getOrder() {
        return order;
    }
}
