package io.cimi.compactjson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Compressor implements Processor {

    protected void moveBytes(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024];
        int read = -1;
        while((read = is.read(buf)) != -1) {
            os.write(buf, 0, read);
        }
    }

}
