package io.cimi.compactjson;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toMap;

public class Runner {

    private Stream<File> getFileStream(String directory) {
        File[] files = new File(directory).listFiles();
        checkNotNull(files, "Directory '%s' does not exist!", directory);
        return Arrays.stream(files);
    }

    private Map<String, Map<String, String>> processFile(File f) {
        Map<String, File> encoded = Arrays.stream(Encoders.values())
                .map(Encoders::getProcessor)
                .collect(toMap(Processor::getExtension, e -> e.process(f)));

        return encoded.entrySet().stream()
                .collect(toMap(Map.Entry::getKey,
                        entry -> Arrays.stream(Compressors.values())
                                .map(Compressors::getProcessor)
                                .collect(toMap(Processor::getExtension,
                                        c -> Utils.readableFileSize(c.process(entry.getValue()))))
                ));
    }

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        Gson gson = new Gson();

        checkArgument(args.length == 1, "Please provide the directory containing your json files as a parameter!");

        System.out.println(gson.toJson(runner.getFileStream(args[0])
                .filter(f -> f.getName().endsWith(".json"))
                .collect(toMap(File::getName, runner::processFile))));
    }
}
