package io.cimi.compactjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class MessagePackEncoder extends Encoder {

    protected MessagePackEncoder() {
        super("msgpack");
    }

    @Override
    protected ObjectMapper buildObjectMapper() {
        MessagePackFactory messagePackFactory = new MessagePackFactory();
        return new ObjectMapper(messagePackFactory);
    }
}
