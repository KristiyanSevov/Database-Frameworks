package productshop.services.api;

import productshop.dto.binding.UsersWrapper;
import productshop.dto.views.soldProducts.UserWithSoldProductsView;
import productshop.dto.views.usersAndProducts.UsersAndProductsView;

import java.util.List;

public interface UserService {
    void saveUsers(UsersWrapper userModels);

    List<UserWithSoldProductsView> findUsersWithSoldProducts();

    UsersAndProductsView getUsersAndProducts();
}
