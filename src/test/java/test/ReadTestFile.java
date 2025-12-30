package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ReadTestFile {
    public static String[] using(String file) {
        try (InputStream inputStream = ReadTestFile.class.getClassLoader().getResourceAsStream(file)) {
            return toReader(inputStream).lines().collect(Collectors.joining("<")).split("<");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedReader toReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static String string(String[] array, String separator) {
        return String.join(separator, array);
    }
}
