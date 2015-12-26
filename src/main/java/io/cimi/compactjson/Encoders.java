package io.cimi.compactjson;

public enum Encoders {
    IDENTITY(new IdentityProcessor()),
    BSON(new BsonEncoder()),
    SMILE(new SmileEncoder()),
    MESSAGE_PACK(new MessagePackEncoder());

    private Processor processor;

    Encoders(Processor processor) {
        this.processor = processor;
    }

    public Processor getProcessor() {
        return processor;
    }
}
