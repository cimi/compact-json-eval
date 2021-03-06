package io.cimi.compactjson;

import com.google.common.base.Throwables;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor extends Compressor {
    protected ZipCompressor() {
        super("zip");
    }

    @Override
    public File process(File inputFile) {
        File compressedFile = new File(inputFile.getAbsolutePath() + ".zip");
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compressedFile));
            FileInputStream is = new FileInputStream(inputFile);
            zos.putNextEntry(new ZipEntry(inputFile.getName()));
            moveBytes(is, zos);
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return compressedFile;
    }

    @Override
    protected OutputStream makeCompressedStream(OutputStream os) throws IOException {
        return new ZipOutputStream(os);
    }
}
