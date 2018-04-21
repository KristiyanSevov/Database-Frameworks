package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "most-popular-item")
    private MostPopularItemDTO item;

    public CategoryExportDTO() {
    }

    public CategoryExportDTO(String name, MostPopularItemDTO item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getItem() {
        return item;
    }

    public void setItem(MostPopularItemDTO item) {
        this.item = item;
    }
}
