package cz.horak.app.statistic;

import cz.horak.app.model.Flight;

public interface FlightStatistic {
    long   getNumberOfFlights();
    double getResult();
    void processFlight(Flight flight);
}
