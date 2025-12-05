package Utils;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONUtils {
    
    public static JSONObject getJSONData(String filePath) {
        try {
            System.out.println("Attempting to read file: " + filePath);
            
            // Try different path approaches
            String[] pathsToTry = {
                filePath,
                "src/test/resources/" + filePath,
                System.getProperty("user.dir") + "/" + filePath,
                System.getProperty("user.dir") + "/src/test/resources/" + filePath
            };
            
            for (String path : pathsToTry) {
                try {
                    System.out.println("Trying path: " + path);
                    
                    if (Files.exists(Paths.get(path))) {
                        String content = new String(Files.readAllBytes(Paths.get(path)));
                        System.out.println("File found and read successfully!");
                        System.out.println("File content: " + content);
                        return new JSONObject(content);
                    } else {
                        System.out.println("File not found at: " + path);
                    }
                } catch (Exception e) {
                    System.out.println("Error reading file at " + path + ": " + e.getMessage());
                }
            }
            
            throw new RuntimeException("File not found at any of the attempted paths");
            
        } catch (Exception e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Alternative method using ClassLoader (recommended for resources)
    public static JSONObject getJSONDataFromResources(String fileName) {
        try {
            // This looks in src/test/resources
            ClassLoader classLoader = JSONUtils.class.getClassLoader();
            String content = new String(
                classLoader.getResourceAsStream(fileName).readAllBytes()
            );
            return new JSONObject(content);
        } catch (Exception e) {
            System.err.println("Error reading JSON from resources: " + e.getMessage());
            return null;
        }
    }
}