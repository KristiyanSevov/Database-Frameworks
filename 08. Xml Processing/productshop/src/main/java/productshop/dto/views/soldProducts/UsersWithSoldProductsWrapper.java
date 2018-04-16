package productshop.dto.views.soldProducts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsWrapper {
    @XmlElement(name = "user")
    private List<UserWithSoldProductsView> users;

    public UsersWithSoldProductsWrapper() {
    }

    public UsersWithSoldProductsWrapper(List<UserWithSoldProductsView> users) {
        this.users = users;
    }

    public List<UserWithSoldProductsView> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsView> users) {
        this.users = users;
    }
}
