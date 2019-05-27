package cz.horak.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.horak.app.statistic.AverageFlightDelayFromToAirport;
import cz.horak.app.statistic.FlightStatistic;
import cz.horak.app.utils.FlightCsvParser;

public class FlightStatisticTest 
{
    File csvFile;
    
    @BeforeEach
    public void loadCsvFile() {
        csvFile = new File("src/test/resources/test1.csv");
    }
    
    @Test
    public void countingOfAverageDelayToLaxAirportShouldReturnRightValue() {
        
        FlightStatistic averageFlightStatiDelay = new AverageFlightDelayFromToAirport("LAX");
        
        FlightCsvParser flightStatistic = FlightCsvParser.createFlightCsvParser(csvFile);
        flightStatistic.processStatisticThroughFlights(averageFlightStatiDelay);
        
        double averageDelay = averageFlightStatiDelay.getResult();

        assertEquals(61.0, averageDelay, "(110 - 19 - 2 + 155 / 4 must be 61.0)");
 
    }
    
    @Test
    public void countingOfAverageDelayAtUnknownAirportShouldReturnZeroValue() {
        
        FlightStatistic averageFlightStatiDelay = new AverageFlightDelayFromToAirport("XXXX");
        
        FlightCsvParser flightStatistic = FlightCsvParser.createFlightCsvParser(csvFile);
        flightStatistic.processStatisticThroughFlights(averageFlightStatiDelay);
        
        double averageDelay = averageFlightStatiDelay.getResult();

        assertEquals(0, averageDelay, "(0 / 0 must be 0)");
 
    }
}
