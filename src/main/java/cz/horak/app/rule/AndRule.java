package cz.horak.app.rule;

import cz.horak.app.model.Flight;

public class AndRule implements FlightRule {

    private FlightRule firstCriteria;
    private FlightRule secondCriteria;
    
    public AndRule(FlightRule firstCriteria, FlightRule secondCriteria) {
        super();
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    public boolean evaluate(Flight flight) {
        return firstCriteria.evaluate(flight) 
                && secondCriteria.evaluate(flight);
    }

}
