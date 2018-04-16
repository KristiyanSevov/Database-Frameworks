package cardealer.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterAdapter extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Date unmarshal(final String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(final Date v) throws Exception {
        return dateFormat.format(v);
    }
}