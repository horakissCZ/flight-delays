package cz.horak.app.statistic;

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
import cz.horak.app.rule.FlightRule;

public class FlightStatistic {

    private static final Logger logger = LoggerFactory.getLogger(FlightStatistic.class);
    
    private final File sourceCsv;
    
    private FlightStatistic(File sourceCsv) {
        super();
        this.sourceCsv = sourceCsv;
    }
    
    public static FlightStatistic createFlightStatistic(File sourceCsv) {
        return new FlightStatistic(sourceCsv);
    }

    public double getAverageDelayOfFlights(FlightRule criteria) {

        long sumOfDelays = 0;
        long numOfFlights = 0;
        Flight flight;
        try(CSVParser parser = new CSVParser(new FileReader(sourceCsv), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                flight = new Flight(record.get("Origin"), Integer.valueOf(record.get("Cancelled")));
    
                if (criteria.evaluate(flight)) {
                    sumOfDelays += Long.valueOf(record.get("DepDelay"));
                    numOfFlights++;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FailedToCountStatisticException("Failed to parse the csv file: " + sourceCsv);
        }

        double averageDelay = 0;
        if(numOfFlights != 0l) {
            averageDelay = (double) sumOfDelays / (double) numOfFlights;
        }
        
        logger.info("Number of flights were: " + numOfFlights + " with average delay: " + averageDelay + " minutes.");

        return averageDelay;
    }
}
