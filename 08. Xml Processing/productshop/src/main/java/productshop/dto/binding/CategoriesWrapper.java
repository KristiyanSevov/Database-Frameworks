package productshop.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesWrapper {

    @XmlElement(name = "category")
    private List<CategoryCreateBindingModel> categories;

    public CategoriesWrapper() {
    }

    public List<CategoryCreateBindingModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryCreateBindingModel> categories) {
        this.categories = categories;
    }
}
