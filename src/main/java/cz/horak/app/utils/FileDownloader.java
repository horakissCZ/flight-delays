package cz.horak.app.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.exception.FailedToCountStatisticException;

public class FileDownloader {
    
    static final Logger logger = LoggerFactory.getLogger(FileDownloader.class);
    
    public static void downloadFile(URL source, File destination) {
        logger.info("Start downloading file from " + source + " to " + destination);
        
        try {
            FileUtils.copyURLToFile(source, destination);
        } catch (IOException e) {
            throw new FailedToCountStatisticException(
                    "Failed to download file from " + source + " to " + destination);
        }
        
        logger.info("File is downloaded to " + destination);
    }
}
