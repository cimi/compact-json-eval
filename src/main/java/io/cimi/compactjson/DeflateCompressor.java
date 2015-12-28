package io.cimi.compactjson;

import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

public class DeflateCompressor extends Compressor {

    protected DeflateCompressor() {
        super("zz");
    }

    @Override
    protected OutputStream makeCompressedStream(OutputStream os) {
        return new DeflaterOutputStream(os);
    }
}
