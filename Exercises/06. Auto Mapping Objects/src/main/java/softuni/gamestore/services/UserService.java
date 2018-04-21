package softuni.gamestore.services;

import softuni.gamestore.models.dtos.bindingModels.UserRegisterBindingModel;
import softuni.gamestore.models.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void registerUser(UserRegisterBindingModel registerDTO) throws Exception;

    User findUserByEmailAndPassword(String email, String password);

    boolean existsEmail(String email);

    void buyGames(User loggedUser, Set<String> shoppingCart);
}
