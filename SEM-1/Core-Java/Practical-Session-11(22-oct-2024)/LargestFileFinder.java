import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.nio.file.attribute.BasicFileAttributes;

public class LargestFileFinder {

    public static void main(String[] args) {

        String directoryPath = "D:\\Documents\\MCA Course\\MCA-Coursework\\SEM-1\\Business-Statistics\\Session-PDF's";
        try {
            Path largestFile = findLargestFile(Paths.get(directoryPath));
            if (largestFile != null) {
                System.out.println(
                        "Largest file: " + largestFile.toString() + " (" + Files.size(largestFile) + " bytes)");
            } else {
                System.out.println("No files found in the directory.");
            }
        } catch (IOException e) {
            System.err.println("Error accessing the directory: " + e.getMessage());
        }
    }

    private static Path findLargestFile(Path directory) throws IOException {
        Path largestFile = null;
        long maxSize = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    long fileSize = Files.size(file);
                    if (fileSize > maxSize) {
                        maxSize = fileSize;
                        largestFile = file;
                    }
                }
            }
        }

        return largestFile;
    }
}