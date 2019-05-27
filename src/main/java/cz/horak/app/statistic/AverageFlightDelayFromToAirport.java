package cz.horak.app.statistic;

import cz.horak.app.model.Flight;

public class AverageFlightDelayFromToAirport implements FlightStatistic {
    
    private long sumOfDelays = 0;
    private long numOfFlights = 0;
    private String airport;
    
    public AverageFlightDelayFromToAirport(String airport) {
        this.airport = airport;
    }
    
    public void processFlight(Flight flight) {
        
        if(flight.getStatus() == Flight.Status.NOT_CANCELLED) {
            if(flight.getFromAirport().equals(airport) 
                    && flight.getDepDelay() != null) {
                addDelayToStatistic(flight.getDepDelay());
            }
            else if(flight.getDestAirport().equals(airport) 
                    && flight.getArrDelay() != null) {
                addDelayToStatistic(flight.getArrDelay());
            }
        }
    }
    
    public void addDelayToStatistic(long delay) {
        sumOfDelays += delay;
        numOfFlights++;
    }

    @Override
    public double getResult() {
        return numOfFlights != 0l ? (double) sumOfDelays / (double) numOfFlights : 0;
    }
    
    @Override
    public long getNumberOfFlights() {
        return numOfFlights;
    }
    
}
