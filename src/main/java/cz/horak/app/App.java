package cz.horak.app;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    
    static final Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        
        BasicConfigurator.configure();
        
    }
}
