package productshop.dto.views.usersAndProducts;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsView {
    @XmlAttribute(name = "count")
    private Integer usersCount;
    @XmlElement(name = "user")
    private List<UserProductsView> users;

    public UsersAndProductsView() {
    }

    public UsersAndProductsView(Integer usersCount, List<UserProductsView> users) {
        this.usersCount = usersCount;
        this.users = users;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserProductsView> getUsers() {
        return users;
    }

    public void setUsers(List<UserProductsView> users) {
        this.users = users;
    }
}
