package io.cimi.compactjson;

import java.io.File;

public class IdentityProcessor extends AbstractProcessor {

    protected IdentityProcessor() {
        super("");
    }

    @Override
    public File process(File inputFile) {
        return inputFile;
    }
}
