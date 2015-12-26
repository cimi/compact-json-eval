package io.cimi.compactjson;

public enum Compressors {
    IDENTITY(new IdentityProcessor()),
    GZIP(new GzipCompressor()),
    DEFLATE(new DeflateCompressor()),
    ZIP(new ZipCompressor());

    private Processor processor;

    Compressors(Processor processor) {
        this.processor = processor;
    }

    public Processor getProcessor() {
        return processor;
    }
}
