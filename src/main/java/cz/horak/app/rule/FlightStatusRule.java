package cz.horak.app.rule;

import cz.horak.app.model.Flight;

public class FlightStatusRule implements FlightRule {
    
    private Flight.Status status;
    
    public FlightStatusRule(Flight.Status status) {
        super();
        this.status = status;
    }

    public boolean evaluate(Flight flight) {
        return flight.getCancelled() == status.getValue();
    }
    
}
