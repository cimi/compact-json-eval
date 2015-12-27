package io.cimi.compactjson;

import com.google.common.base.Throwables;

import java.io.*;

public abstract class Compressor implements Processor {

    protected final String extension;

    protected Compressor(String extension) {
        this.extension = extension;
    }

    public File process(File inputFile) {
        File outputFile = makeOutputFile(inputFile);
        try {
            moveBytes(new FileInputStream(inputFile), new FileOutputStream(outputFile));
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }

    private File makeOutputFile(File inputFile) {
        String fileName = inputFile.getAbsolutePath() + "." + extension;
        return new File(fileName);
    }

    protected void moveBytes(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024];
        int read = -1;
        while((read = is.read(buf)) != -1) {
            os.write(buf, 0, read);
        }
    }

}
