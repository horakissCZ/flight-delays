package cz.horak.app.rule;

import cz.horak.app.model.Flight;

public interface FlightRule {
    boolean evaluate(Flight flight);
}
