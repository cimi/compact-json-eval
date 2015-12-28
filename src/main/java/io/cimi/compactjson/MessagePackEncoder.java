package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MessagePackEncoder extends Encoder {

    protected MessagePackEncoder() {
        super("msgpack");
    }

    private ObjectMapper buildMessagePackObjectMapper() {
        MessagePackFactory messagePackFactory = new MessagePackFactory();
        return new ObjectMapper(messagePackFactory);
    }

    @Override
    public File process(File inputFile) {
        ObjectMapper msgpack = buildMessagePackObjectMapper();
        File outputFile = new File(inputFile.getAbsolutePath() + ".msgpack");
        try {
            Map<String, Object> content = Utils.parseJsonFile(inputFile);
            msgpack.writeValue(outputFile, content);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return outputFile;
    }
}
