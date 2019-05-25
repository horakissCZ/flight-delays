package cz.horak.app.model;

public class Flight {
    
    private String originAiport;
    private long depDelay;
    private int cancelled;
    
    public Flight() {
        super();
    }
    
    public Flight(String originAiport, int cancelled) {
        super();
        this.originAiport = originAiport;
        this.cancelled = cancelled;
    }
    
    public String getOriginAiport() {
        return originAiport;
    }
    
    public long getDepDelay() {
        return depDelay;
    }
    
    public int getCancelled() {
        return cancelled;
    }
    
    @Override
    public String toString() {
        return "Flight [originAiport=" + originAiport + ", depDelay=" + depDelay + ", cancelled=" + cancelled + "]";
    }
    
    public enum Status {
        
        NOT_CANCELLED(0),
        CANCELLED(1);

        private final int value;

        private Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
