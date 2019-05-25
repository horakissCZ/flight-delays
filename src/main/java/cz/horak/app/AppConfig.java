package cz.horak.app;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.exception.FailedToCountStatisticException;

public class AppConfig {

    static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    
    private static final String URL_DEFAULT = "http://stat-computing.org/dataexpo/2009/1989.csv.bz2";
    private static final String WORKING_DIR_DEFAULT = "/var/tmp";
    
    private final URL  source;
    private final File workingDir;
    private final File compressedSource;
    private final File csvFile;
    
    private AppConfig(URL source, File workingDir, File compressedSource, File csvFile) {
        super();
        this.source = source;
        this.workingDir = workingDir;
        this.compressedSource = compressedSource;
        this.csvFile = csvFile;
    }

    public static AppConfig createAppConfig(String sourceUrl, String workingDir) {

        String workingDirectoryPath = workingDir != null ? workingDir : WORKING_DIR_DEFAULT;
        String sourcePath = sourceUrl != null ? sourceUrl : URL_DEFAULT;
        
        File workingDirectory = new File(workingDirectoryPath);
        
        if(!workingDirectory.canWrite()) {
            throw new FailedToCountStatisticException(
                    "Application doesn't have permission to write to " + workingDir);
        }
        
        URL source;
        try {
            source = new URL(sourcePath);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new FailedToCountStatisticException("Malformed URL has occurred");
        }

        String downloadedFilePath = FilenameUtils.getName(sourcePath);
        File downloadedSource = new File(workingDirectory, downloadedFilePath);
        
        String csvFileName = FilenameUtils.removeExtension(downloadedFilePath);
        File csvFile = new File(workingDirectory, csvFileName);
        
        return new AppConfig(source, workingDirectory, downloadedSource, csvFile);
    }

    public URL getSource() {
        return source;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public File getCompressedSource() {
        return compressedSource;
    }

    public File getCsvFile() {
        return csvFile;
    }

    @Override
    public String toString() {
        return "AppConfig [source=" + source + ", workingDir=" + workingDir + ", compressedSource=" + compressedSource
                + ", csvFile=" + csvFile + "]";
    }
 
}
