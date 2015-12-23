package io.cimi.compactjson;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor implements Processor {
    @Override
    public File process(File inputFile) {
        File compressedFile = new File(inputFile.getAbsolutePath() + ".zip");
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compressedFile));
            FileInputStream is = new FileInputStream(inputFile);
            byte[] buf = new byte[1024];
            int read = -1;
            zos.putNextEntry(new ZipEntry(inputFile.getName()));
            while( (read = is.read(buf)) != -1) {
                zos.write(buf, 0, read);
            }
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return compressedFile;
    }
}
