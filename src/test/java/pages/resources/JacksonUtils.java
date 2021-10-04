package pages.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
        String absolutePath = System.getProperty("user.dir") + "/src/test/resources/"+ fileName;
        InputStream iss = new FileInputStream(absolutePath);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(iss, T);
    }
}
