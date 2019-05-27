package cz.horak.app.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.exception.FailedToCountStatisticException;
import cz.horak.app.model.Flight;
import cz.horak.app.statistic.FlightStatistic;

public class FlightCsvParser {

    private static final Logger logger = LoggerFactory.getLogger(FlightCsvParser.class);
    
    private final File sourceCsv;
    
    private FlightCsvParser(File sourceCsv) {
        super();
        this.sourceCsv = sourceCsv;
    }
    
    public static FlightCsvParser createFlightCsvParser(File sourceCsv) {
        return new FlightCsvParser(sourceCsv);
    }

    public void processStatisticThroughFlights(FlightStatistic averageFlightDelay) {

        Flight flight;
        try(CSVParser parser = new CSVParser(new FileReader(sourceCsv), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                
                flight = Flight.createFlight(record.get("Origin"), record.get("DepDelay"), record.get("Dest"), record.get("ArrDelay"), record.get("Cancelled"));
                averageFlightDelay.processFlight(flight);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FailedToCountStatisticException("Failed to parse the csv file: " + sourceCsv);
        }

    }
}
