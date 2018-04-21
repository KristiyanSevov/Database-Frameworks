package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.dtos.bindingModels.UserRegisterBindingModel;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;
import softuni.gamestore.repositories.GameRepository;
import softuni.gamestore.repositories.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(ModelMapper mapper,
                           UserRepository userRepository,
                           GameRepository gameRepository,
                           Validator validator) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.validator = validator;
    }

    @Override
    public void registerUser(UserRegisterBindingModel registerDTO) throws Exception {
        User user = mapper.map(registerDTO, User.class);
        if (validator.validate(user).size() != 0) {
            throw new Exception("Something unexpected went wrong");
        }
        if (userRepository.findAll().size() == 0) {
            user.setAdmin(true);
        } else {
            user.setAdmin(false);
        }
        userRepository.save(user);
}

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public void buyGames(User user, Set<String> titles) {
        System.out.println("Successfully bought games:");
        for (String title : titles) {
            Game game = gameRepository.findGameByTitle(title);
            if (game != null) {
                user.getGames().add(game);
            }
            System.out.println(" -" + title);
        }
        userRepository.save(user);
    }

}
