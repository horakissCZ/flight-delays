package cz.horak.app.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.horak.app.exception.FailedToCountStatisticException;

public class ArchiveManager {
    
    static final Logger logger = LoggerFactory.getLogger(ArchiveManager.class);
    
    public static void uncompressBZip2(File compressed, File uncompressed) {

        logger.info("Start uncompress file " + compressed);
        
        try (InputStream fin = new FileInputStream(compressed);
                BufferedInputStream in = new BufferedInputStream(fin);
                OutputStream out = new FileOutputStream(uncompressed);
                BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in)) {
            int buffersize = 1024;
            final byte[] buffer = new byte[buffersize];
            int n = 0;

            while (-1 != (n = bzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            throw new FailedToCountStatisticException(
                    "Failed to uncompress file " + compressed + " to " + uncompressed);
        } 
        
        logger.info("File is uncompressed to " + uncompressed);
    }
    
}
