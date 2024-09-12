package javaversionfeatures.java11;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandlingDemo {
	public static void main(String[] args) throws IOException {
		Path filePath = Paths.get("D:\\suthanfile.txt");

        String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
        System.out.println(fileContent);
	}

}
