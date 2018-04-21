package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesFrequentItemsXMLExportDTO {
    @XmlElement(name = "category")
    private List<CategoryExportDTO> categories;

    public CategoriesFrequentItemsXMLExportDTO() {
    }

    public CategoriesFrequentItemsXMLExportDTO(List<CategoryExportDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryExportDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryExportDTO> categories) {
        this.categories = categories;
    }
}
