package io.cimi.compactjson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

public class Runner {

    private Stream<File> getFileStream(String directory) {
        File[] files = new File(directory).listFiles();
        checkNotNull(files, "Directory '%s' does not exist!", directory);
        return Arrays.stream(files);
    }

    private List<File> processFile(File f) {
        return Arrays.stream(Encoders.values())
                .map(encoder -> encoder.getProcessor().process(f))
                .flatMap(encoded -> Arrays.stream(Compressors.values())
                        .map(compressor -> compressor.getProcessor().process(encoded)))
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
