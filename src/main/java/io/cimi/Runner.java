package io.cimi;

import com.google.common.collect.ImmutableList;
import io.cimi.compression.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

public class Runner {
    private static final String RESOURCE_DIRECTORY = "/Users/ciminian/code/json-compression/src/main/resources/";

    private Stream<File> getFileStream(String directory) {
        File[] files = new File(directory).listFiles();
        checkNotNull(files, "directory %s does not exist!", directory);
        return Arrays.stream(files);
    }

    private List<File> processFile(File f) {
        List<Processor> encoders = ImmutableList.of(new IdentityProcessor(), new SmileEncoder());
        List<Processor> compressors = ImmutableList.of(new IdentityProcessor(), new GzipCompressor());
        return encoders.stream()
                .map(encoder -> encoder.process(f))
                .flatMap(encoded -> compressors
                        .stream()
                        .map(compressor -> compressor.process(encoded)))
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();

        runner.getFileStream(RESOURCE_DIRECTORY)
                .filter(f -> f.getName().endsWith(".json"))
                .map(runner::processFile)
                .forEach(Utils::outputInformation);
    }
}
