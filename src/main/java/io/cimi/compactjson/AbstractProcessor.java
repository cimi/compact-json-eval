package io.cimi.compactjson;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractProcessor implements Processor {
    protected final String extension;

    protected AbstractProcessor(String extension) {
        this.extension = checkNotNull(extension, "extension is null!");
    }

    public String getExtension() {
        return extension;
    }
}
