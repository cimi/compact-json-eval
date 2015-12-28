package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import de.undercouch.bson4jackson.BsonFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BsonEncoder extends Encoder {

    protected BsonEncoder() {
        super("bson");
    }

    private ObjectMapper buildBsonObjectMapper() {
        BsonFactory factory = new BsonFactory();
        // by default this might load everything in memory
        // see http://www.michel-kraemer.com/binary-json-with-bson4jackson
        // I don't really care for the purpose of this eval, so leaving as is
        // factory.enable(BsonGenerator.Feature.ENABLE_STREAMING);
        return new ObjectMapper(factory);
    }

    @Override
    public File process(File inputFile) {
        ObjectMapper bson = buildBsonObjectMapper();
        File outputFile = new File(inputFile.getAbsolutePath() + ".bson");
        try {
            Map<String, Object> content = Utils.parseJsonFile(inputFile);
            bson.writeValue(outputFile, content);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }
}
