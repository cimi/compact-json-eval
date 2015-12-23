package io.cimi.compression;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class Utils {
    private static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private static String buildFileInfo(File file) {
        return file.getName() + " -> " + readableFileSize(file.length());
    }

    public static void outputInformation(List<File> fileList) {
        fileList.stream().map(Utils::buildFileInfo).forEach(System.out::println);
        System.out.println("------");
    }
}
