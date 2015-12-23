package io.cimi.compression;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import com.google.common.base.Throwables;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmileEncoder implements Processor {
    private static ObjectMapper getSmileObjectMapper() {
        SmileFactory smileFactory = new SmileFactory();
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, true);
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, true);
        smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_HEADER, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_END_MARKER, true);
        smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, true);
        return new ObjectMapper(smileFactory);
    }

    private Map<String, Object> parseJsonFile(File file) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        return mapper.readValue(file, typeRef);
    }

    public File process(File input) {
        ObjectMapper smile = getSmileObjectMapper();
        File outputFile = new File(input.getAbsolutePath() + ".smile");
        try {
            Map<String, Object> content = parseJsonFile(input);
            smile.writeValue(outputFile, content);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }
}
