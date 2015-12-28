package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;

public class BsonEncoder extends Encoder {

    protected BsonEncoder() {
        super("bson");
    }

    @Override
    protected ObjectMapper buildObjectMapper() {
        BsonFactory factory = new BsonFactory();
        // by default this might load everything in memory
        // see http://www.michel-kraemer.com/binary-json-with-bson4jackson
        // I don't really care for the purpose of this eval, so leaving as is
        // factory.enable(BsonGenerator.Feature.ENABLE_STREAMING);
        return new ObjectMapper(factory);
    }
}
