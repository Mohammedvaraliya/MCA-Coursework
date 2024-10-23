import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileCopy <source file> <destination file>");
            return;
        }

        String sourceFilePath = args[0];
        String destinationFilePath = args[1];

        File sourceFile = new File(sourceFilePath);

        if (!sourceFile.exists()) {
            System.err.println("Source file does not exist: " + sourceFilePath);
            return;
        }

        try (FileInputStream fis = new FileInputStream(sourceFile);
                FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied successfully to: " + destinationFilePath);
        } catch (IOException e) {
            System.err.println("Error during file copy: " + e.getMessage());
        }
    }
}