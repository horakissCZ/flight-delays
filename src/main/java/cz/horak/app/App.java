package cz.horak.app;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.statistic.AverageFlightDelayFromToAirport;
import cz.horak.app.statistic.FlightStatistic;
import cz.horak.app.utils.ArchiveManager;
import cz.horak.app.utils.FileDownloader;
import cz.horak.app.utils.FlightCsvParser;

public class App {
    
    static final Logger logger = LoggerFactory.getLogger(App.class);
    
    private static final String AIRPORT_DEFAULT = "LAX";
    
    public static void main(String[] args) {
        
        BasicConfigurator.configure();
        
        // Get users' parameters(optional)
        String airportParam = System.getProperty("airport");
        String sourceUrlParam = System.getProperty("url");
        String workingDirParam = System.getProperty("workingDir");
        
        AppConfig appConfig = AppConfig.createAppConfig(sourceUrlParam, workingDirParam);
        logger.info("Use the following " + appConfig);
        
        FileDownloader.downloadFile(appConfig.getSource(), appConfig.getCompressedSource());
        
        ArchiveManager.uncompressBZip2(appConfig.getCompressedSource(), appConfig.getCsvFile());
        
        // Create rules to get only required rows
        String airport = airportParam != null ? airportParam : AIRPORT_DEFAULT;
        FlightStatistic averageFlightStatiDelay = new AverageFlightDelayFromToAirport(airport);
        
        FlightCsvParser flightStatistic = FlightCsvParser.createFlightCsvParser(appConfig.getCsvFile());
        flightStatistic.processStatisticThroughFlights(averageFlightStatiDelay);
        
        long   numberOfFlights = averageFlightStatiDelay.getNumberOfFlights();
        double averageDelay = averageFlightStatiDelay.getResult();
        
        logger.info("The number of from/to flights from " + airport + " were " + numberOfFlights + ".");
        logger.info("Average delay was: " + averageDelay + " minutes.");
       
        System.exit(0);
    }
}
