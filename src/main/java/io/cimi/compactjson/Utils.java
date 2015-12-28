package io.cimi.compactjson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String readableFileSize(File file) {
        return readableFileSize(file.length());
    }

    private static String buildFileInfo(File file) {
        return formatMarkdownRow(file.getName(), readableFileSize(file.length()));
    }

    private static String formatMarkdownRow(String firstCol, String secondCol) {
        return "| " + String.format("%1$-50s", firstCol)
                + " | "
                + String.format("%1$-10s", secondCol) + " |";
    }

    public static Map<String, Object> parseJsonFile(File file) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        return mapper.readValue(file, typeRef);
    }

    public static void outputInformation(List<File> fileList) {
        System.out.println(formatMarkdownRow("File", "Size"));
        System.out.println(formatMarkdownRow(Strings.repeat("-", 50), Strings.repeat("-", 10)));
        fileList.stream().map(Utils::buildFileInfo).forEach(System.out::println);
        System.out.println();
    }
}
