package io.cimi.compression;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipCompressor implements Processor {

    public File process(File inputFile) {
        File compressedFile = new File(inputFile.getAbsolutePath() + ".gz");
        try {
            GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(compressedFile));
            FileInputStream is = new FileInputStream(inputFile);
            byte[] buf = new byte[1024];
            int read = -1;
            while( (read = is.read(buf)) != -1) {
                gzos.write(buf, 0, read);
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return compressedFile;
    }
}
