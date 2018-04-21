package productshop.parsers;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@Component
public class XmlParser {

    private JAXBContext jaxbContext;

    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        T object;
        try (
                InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        ) {
            object = (T) unmarshaller.unmarshal(inputStream);
        }
        return object;
    }

    public <T> String write(T object) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);

        String result = sw.toString();
        return result;
    }
}