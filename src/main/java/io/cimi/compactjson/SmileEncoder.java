package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;

public class SmileEncoder extends Encoder {
    protected SmileEncoder() {
        super("smile");
    }

    @Override
    protected ObjectMapper buildObjectMapper() {
        SmileFactory smileFactory = new SmileFactory();
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, true);
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, true);
        smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_HEADER, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_END_MARKER, true);
        smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, true);
        return new ObjectMapper(smileFactory);
    }
}
