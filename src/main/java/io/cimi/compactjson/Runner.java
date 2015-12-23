package io.cimi.compactjson;

import com.google.common.collect.ImmutableList;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

public class Runner {

    private static List<Processor> ENCODERS = ImmutableList.of(
            new IdentityProcessor(),
            new SmileEncoder(),
            new BsonEncoder());

    private static List<Processor> COMPRESSORS = ImmutableList.of(
            new IdentityProcessor(),
            new GzipCompressor());

    private Stream<File> getFileStream(String directory) {
        File[] files = new File(directory).listFiles();
        checkNotNull(files, "Directory '%s' does not exist!", directory);
        return Arrays.stream(files);
    }

    private List<File> processFile(File f) {
        return ENCODERS.stream()
                .map(encoder -> encoder.process(f))
                .flatMap(encoded -> COMPRESSORS
                        .stream()
                        .map(compressor -> compressor.process(encoded)))
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();

        checkArgument(args.length == 1, "Please provide the directory containing your json files as a parameter!");

        runner.getFileStream(args[0])
                .filter(f -> f.getName().endsWith(".json"))
                .map(runner::processFile)
                .forEach(Utils::outputInformation);
    }
}
