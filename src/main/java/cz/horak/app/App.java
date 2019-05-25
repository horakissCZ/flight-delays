package cz.horak.app;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.model.Flight;
import cz.horak.app.rule.AndRule;
import cz.horak.app.rule.FlightRule;
import cz.horak.app.rule.FlightStatusRule;
import cz.horak.app.rule.OriginRule;
import cz.horak.app.statistic.FlightStatistic;
import cz.horak.app.utils.ArchiveManager;
import cz.horak.app.utils.FileDownloader;

public class App {
    
    static final Logger logger = LoggerFactory.getLogger(App.class);
    
    private static final String AIRPORT_DEFAULT = "LAX";
    
    public static void main(String[] args) {
        
        BasicConfigurator.configure();
        
        // Get users' parameters(optional)
        String airportParam = System.getProperty("airport");
        String sourceUrlParam = System.getProperty("url");
        String workingDirParam = System.getProperty("workingDir");
        
        // Create rules to get only required rows
        String aiport = airportParam != null ? airportParam : AIRPORT_DEFAULT;
        FlightRule fromAirport = new OriginRule(aiport); 
        FlightRule notCancelled = new FlightStatusRule(Flight.Status.NOT_CANCELLED);
        FlightRule fromAirportAndNotCancelled = new AndRule(fromAirport, notCancelled);
        
        AppConfig appConfig = AppConfig.createAppConfig(sourceUrlParam, workingDirParam);
        logger.info("Use the following " + appConfig);
        
        FileDownloader.downloadFile(appConfig.getSource(), appConfig.getCompressedSource());
        
        ArchiveManager.uncompressBZip2(appConfig.getCompressedSource(), appConfig.getCsvFile());
        
        FlightStatistic flightStatistic = FlightStatistic.createFlightStatistic(appConfig.getCsvFile());
        double averageDelay = flightStatistic.getAverageDelayOfFlights(fromAirportAndNotCancelled);
        
        logger.info("The average delay of the flights at aiport " + aiport + " was " + averageDelay);
       
        System.exit(0);
    }
}
