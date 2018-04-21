package softuni.gamestore.services;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.dtos.bindingModels.GameRegisterBindingModel;
import softuni.gamestore.models.dtos.views.GameDetailsView;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;
import softuni.gamestore.repositories.GameRepository;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final ModelMapper mapper;
    private final GameRepository gameRepository;
    private final Validator validator;

    public GameServiceImpl(ModelMapper mapper, GameRepository gameRepository, Validator validator) {
        this.mapper = mapper;
        this.gameRepository = gameRepository;
        this.validator = validator;
    }

    @Override
    public void registerGame(GameRegisterBindingModel model) throws InvalidArgumentException {
        Game game = mapper.map(model, Game.class);
        if (validator.validate(game).size() != 0) {
            throw new InvalidArgumentException(new String[]{"Validation failed!"});
        } else {
            gameRepository.save(game);
        }
    }

    @Override
    public Optional<Game> findGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public void saveUpdatedGame(Game game) throws InvalidArgumentException {
        if (validator.validate(game).size() != 0) {
            throw new InvalidArgumentException(new String[]{"Validation failed!"});
        } else {
            gameRepository.save(game);
        }
    }

    @Override
    public void deleteGame(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isPresent()) {
            System.out.println("No such game.");
        } else {
            Game toDelete = game.get();
            String title = toDelete.getTitle();
            gameRepository.delete(toDelete);
            System.out.println("Deleted " + title);
        }
    }

    @Override
    public GameDetailsView findGameByTitle(String gameTitle) {
        Game game = gameRepository.findGameByTitle(gameTitle);
        if (game == null) {
            return null;
        }
        return mapper.map(game, GameDetailsView.class);
    }

    @Override
    public void printAllGames() {
        gameRepository.findAll().forEach(x -> System.out.printf("%s %.2f%n", x.getTitle(), x.getPrice()));
    }
}
