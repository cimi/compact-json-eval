package io.cimi.compactjson;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

public class DeflateCompressor extends Compressor {
    @Override
    public File process(File inputFile) {
        File compressedFile = new File(inputFile.getAbsolutePath() + ".zz");
        try {
            DeflaterOutputStream dos = new DeflaterOutputStream(new FileOutputStream(compressedFile));
            FileInputStream is = new FileInputStream(inputFile);
            moveBytes(is, dos);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return compressedFile;
    }
}
