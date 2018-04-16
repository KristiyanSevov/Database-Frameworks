package productshop.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UsersAndProductsView implements Serializable {
    @Expose
    private Integer usersCount;
    @Expose
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
