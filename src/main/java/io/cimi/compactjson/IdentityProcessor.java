package io.cimi.compactjson;

import java.io.File;

public class IdentityProcessor implements Processor {

    @Override
    public File process(File f) {
        return f;
    }
}
