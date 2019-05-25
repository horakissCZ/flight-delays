package cz.horak.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.horak.app.model.Flight;
import cz.horak.app.rule.AndRule;
import cz.horak.app.rule.FlightRule;
import cz.horak.app.rule.FlightStatusRule;
import cz.horak.app.rule.OriginRule;
import cz.horak.app.statistic.FlightStatistic;

public class FlightStatisticTest 
{
    File csvFile;
    
    @BeforeEach
    public void loadCsvFile() {
        csvFile = new File("src/test/resources/test1.csv");
    }
    
    @Test
    public void countingOfAverageDelayAtLaxAirportShouldReturnRightValue() {
        
        FlightRule fromAirport = new OriginRule("LAX"); 
        FlightRule notCancelled = new FlightStatusRule(Flight.Status.NOT_CANCELLED);
        FlightRule fromAirportAndNotCancelled = new AndRule(fromAirport, notCancelled);
        
        FlightStatistic flightStatistic = FlightStatistic.createFlightStatistic(csvFile);
        double averageDelay = flightStatistic.getAverageDelayOfFlights(fromAirportAndNotCancelled);

        assertEquals(50.5, averageDelay, "(109 + 0 - 2 + 95 / 4 must be 50.5)");
 
    }
    
    @Test
    public void countingOfAverageDelayAtUnknownAirportShouldReturnZeroValue() {
        
        FlightRule fromAirport = new OriginRule("XXXX"); 
        FlightRule notCancelled = new FlightStatusRule(Flight.Status.NOT_CANCELLED);
        FlightRule fromAirportAndNotCancelled = new AndRule(fromAirport, notCancelled);
        
        FlightStatistic flightStatistic = FlightStatistic.createFlightStatistic(csvFile);
        double averageDelay = flightStatistic.getAverageDelayOfFlights(fromAirportAndNotCancelled);

        assertEquals(0, averageDelay, "(0 / 0 must be 0)");
 
    }
}
