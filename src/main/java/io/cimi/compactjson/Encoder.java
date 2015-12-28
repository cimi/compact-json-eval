package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class Encoder extends AbstractProcessor {

    protected Encoder(String extension) {
        super(extension);
    }

    protected abstract ObjectMapper buildObjectMapper();

    @Override
    public File process(File inputFile) {
        ObjectMapper encodedMapper = buildObjectMapper();
        File outputFile = new File(inputFile.getAbsolutePath() + "." + extension);
        try {
            Map<String, Object> content = Utils.parseJsonFile(inputFile);
            encodedMapper.writeValue(outputFile, content);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }
}
