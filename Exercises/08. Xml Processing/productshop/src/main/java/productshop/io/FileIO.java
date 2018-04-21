package productshop.io;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIO {
    public String read(String file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream inputStream = getClass().getResourceAsStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    public void write(String fileContent, String file) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources" + file;
        try (
                OutputStream outputStream = new FileOutputStream(path);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            bufferedWriter.write(fileContent);
        }
    }
}
