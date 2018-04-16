package productshop.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWrapper {

    @XmlElement(name = "user")
    private List<UserCreateBindingModel> users;

    public UsersWrapper() {
    }

    public List<UserCreateBindingModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserCreateBindingModel> users) {
        this.users = users;
    }
}
