package io.cimi.compactjson;

import java.io.File;

public interface Processor {
    File process(File inputFile);
    String getExtension();
}
