package org.softuni.mostwanted.parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {
    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) throws IOException {
        return this.gson.toJson(object);
    }
}