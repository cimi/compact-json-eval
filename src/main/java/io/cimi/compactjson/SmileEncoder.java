package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import com.google.common.base.Throwables;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SmileEncoder implements Processor {
    private ObjectMapper buildSmileObjectMapper() {
        SmileFactory smileFactory = new SmileFactory();
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, true);
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, true);
        smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_HEADER, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_END_MARKER, true);
        smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, true);
        return new ObjectMapper(smileFactory);
    }

    public File process(File input) {
        ObjectMapper smile = buildSmileObjectMapper();
        File outputFile = new File(input.getAbsolutePath() + ".smile");
        try {
            Map<String, Object> content = Utils.parseJsonFile(input);
            smile.writeValue(outputFile, content);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }
}
