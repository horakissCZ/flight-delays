package cz.horak.app.model;

import java.util.HashMap;
import java.util.Map;

public class Flight {
    
    private String fromAirport;
    private Long depDelay;
    private String destAirport;
    private Long arrDelay;
    private Status status;
    
    private Flight() {
        super();
    }

    private Flight(String fromAirport, Long depDelay, String destAirport, Long arrDelay, Status status) {
        super();
        this.fromAirport = fromAirport;
        this.depDelay = depDelay;
        this.destAirport = destAirport;
        this.arrDelay = arrDelay;
        this.status = status;
    }

    public static Flight createFlight(String fromAirport, String depDelay, String toAirport, String arrDelay,  String status) {
        
        Status flightStatus;
        try {
            flightStatus = Status.valueOf(Integer.valueOf(status));
        } catch (NumberFormatException e) {
            flightStatus = Status.UNKNOW;
        }
        
        Long departmentDelay;
        try {
            departmentDelay = Long.valueOf(depDelay);
        } catch (NumberFormatException e) {
            departmentDelay = null;
        }
        
        Long arrivalDelay;
        try {
            arrivalDelay = Long.valueOf(arrDelay);
        } catch (NumberFormatException e) {
            arrivalDelay = null;
        }
        
        return new Flight(fromAirport, departmentDelay, toAirport, arrivalDelay, flightStatus);
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public Long getDepDelay() {
        return depDelay;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public Long getArrDelay() {
        return arrDelay;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Flight [fromAirport=" + fromAirport + ", destAirport=" + destAirport + ", depDelay=" + depDelay
                + ", status=" + status + "]";
    }

    public enum Status {
        
        UNKNOW(-5),
        NOT_CANCELLED(0),
        CANCELLED(1);

        private final int value;

        private static Map<Integer, Status> map = new HashMap<>();
        
        private Status(int value) {
            this.value = value;
        }
        
        static {
            for (Status status : Status.values()) {
                map.put(status.value, status);
            }
        }
        
        public static Status valueOf(int status) {
            return map.get(status);
        }

        public int getValue() {
            return value;
        }
    }
}
