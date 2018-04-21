package productshop.services.api;

import productshop.dto.binding.UserCreateBindingModel;
import productshop.dto.views.UserWithSoldProductsView;
import productshop.dto.views.UsersAndProductsView;

import java.util.List;

public interface UserService {
    void saveUsers(UserCreateBindingModel[] userModels);

    List<UserWithSoldProductsView> findUsersWithSoldProducts();

    UsersAndProductsView getUsersAndProducts();
}
