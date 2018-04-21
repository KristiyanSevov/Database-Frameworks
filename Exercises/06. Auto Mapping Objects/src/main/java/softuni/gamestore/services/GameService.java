package softuni.gamestore.services;

import com.sun.javaws.exceptions.InvalidArgumentException;
import softuni.gamestore.models.dtos.bindingModels.GameRegisterBindingModel;
import softuni.gamestore.models.dtos.views.GameDetailsView;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;

import java.util.Optional;
import java.util.Set;

public interface GameService  {

    void registerGame(GameRegisterBindingModel model) throws InvalidArgumentException;

    Optional<Game> findGameById(Long id);

    void saveUpdatedGame(Game game) throws InvalidArgumentException;

    void deleteGame(Long id);

    GameDetailsView findGameByTitle(String gameTitle);

    void printAllGames();
}
