package cz.horak.app.rule;

import cz.horak.app.model.Flight;

public class OriginRule implements FlightRule {

    private String originAiport;
    
    public OriginRule(String originAiport) {
        super();
        this.originAiport = originAiport;
    }

    public boolean evaluate(Flight flight) {
        return flight.getOriginAiport().equals(originAiport);
    }
    
}
