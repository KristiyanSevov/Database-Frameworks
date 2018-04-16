package productshop.dto.views.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesWrapperView {
    @XmlElement(name = "category")
    private List<CategoriesByCountView> categories;

    public CategoriesWrapperView() {
    }

    public CategoriesWrapperView(List<CategoriesByCountView> categories) {
        this.categories = categories;
    }

    public List<CategoriesByCountView> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesByCountView> categories) {
        this.categories = categories;
    }
}
