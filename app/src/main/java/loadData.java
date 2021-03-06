import java.io.*;
import java.net.URL;

public class LoadData {

    public LoadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/Schools.csv")))) {
            new Data(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveData(String url, String doc) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream(doc)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
