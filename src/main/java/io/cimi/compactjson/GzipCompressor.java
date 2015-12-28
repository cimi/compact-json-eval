package io.cimi.compactjson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompressor extends Compressor {

    protected GzipCompressor() {
        super("gz");
    }

    @Override
    protected OutputStream makeCompressedStream(OutputStream os) throws IOException {
        return new GZIPOutputStream(os);
    }
}
