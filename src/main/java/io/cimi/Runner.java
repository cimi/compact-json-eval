package io.cimi;

import io.cimi.compression.GzipProcessor;
import io.cimi.compression.SmileProcessor;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Runner {
    private static final String RESOURCE_DIRECTORY = "/Users/ciminian/code/problems/java/src/main/resources/";

    private static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private void listFileSizes(String directory) {
        File[] files = new File(directory).listFiles();
        for (File file : files) {
            System.out.println(file.getName() + " -> " + readableFileSize(file.length()));
        }
    }

    private File readResource(String resourceName) {
        return new File(getClass().getClassLoader().getResource(resourceName).getFile());
    }

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();

        SmileProcessor smile = new SmileProcessor();
        GzipProcessor gzip = new GzipProcessor();

        File inputFile = runner.readResource("item-master-prod.json");

        File encodedFile = smile.process(inputFile);
        File compressedSmile = gzip.process(encodedFile);
        File compressedJson = gzip.process(inputFile);

        runner.listFileSizes(RESOURCE_DIRECTORY);
    }
}
