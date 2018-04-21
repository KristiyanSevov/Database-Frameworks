package softuni.gamestore;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.gamestore.models.dtos.bindingModels.GameRegisterBindingModel;
import softuni.gamestore.models.dtos.bindingModels.UserRegisterBindingModel;
import softuni.gamestore.models.dtos.views.GameDetailsView;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;
import softuni.gamestore.services.GameService;
import softuni.gamestore.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public Runner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User loggedUser = null;
        Set<String> shoppingCart = new HashSet<>();

        //ugly code but no time to refactor
        while (true) {
            String[] inputs = reader.readLine().split("\\|");
            String command = inputs[0];
            switch (command) {
                case "RegisterUser":
                    String email = inputs[1];
                    String password = inputs[2];
                    String confirmPassword = inputs[3];
                    String fullName = inputs[4];
                    if (!(email.contains("@") && email.contains("."))) {
                        System.out.println("Incorrect email.");
                        continue;
                    }
                    if (!(password.length() >= 6 && password.matches(".*[A-Z].*") &&
                            password.matches(".*[a-z].*") && password.matches(".*[0-9].*"))) {
                        System.out.println("Password must be 6 or more symbols and contain a lowercase letter, " +
                                "uppercase letter and a digit.");
                        continue;
                    }
                    if (!password.equals(confirmPassword)) {
                        System.out.println("The passwords don't match.");
                        continue;
                    }
                    if (userService.existsEmail(email)) {
                        System.out.println("User with this email has already been registered.");
                        continue;
                    }
                    UserRegisterBindingModel registerDTO =
                            new UserRegisterBindingModel(email, password, confirmPassword, fullName);
                    userService.registerUser(registerDTO);
                    System.out.println(fullName + " was registered");
                    break;
                case "LoginUser":
                    String loginEmail = inputs[1];
                    String loginPassword = inputs[2];
                    loggedUser = userService.findUserByEmailAndPassword(loginEmail, loginPassword);
                    if (loggedUser != null) {
                        System.out.println("Successfully logged in " + loggedUser.getFullName());
                        shoppingCart.clear();
                    } else {
                        System.out.println("Incorrect username / password");
                    }
                    break;
                case "LogoutUser":
                    if (loggedUser != null) {
                        System.out.printf("User %s successfully logged out%n", loggedUser.getFullName());
                        loggedUser = null;
                        shoppingCart.clear();
                    } else {
                        System.out.println("Cannot log out. No user was logged in.");
                    }
                    break;
                case "AddGame":
                    if (loggedUser == null || !loggedUser.getAdmin()) {
                        System.out.println("Please log in as admin.");
                        continue;
                    }
                    String title = inputs[1];
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(inputs[2]));
                    Double size = Double.parseDouble(inputs[3]);
                    String youtubeURL = inputs[4];
                    String thumbnailURL = inputs[5];
                    String description = inputs[6];
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date releaseDate = df.parse(inputs[7]);
                    if (!youtubeURL.contains("www.youtube.com")) {
                        System.out.println("Trailer video must be from youtube.");
                        continue;
                    }
                    String trailerID = youtubeURL.substring(youtubeURL.length() - 11);
                    GameRegisterBindingModel model = new GameRegisterBindingModel(title, price, size,
                            trailerID, thumbnailURL, description, releaseDate);
                    try {
                        gameService.registerGame(model);
                        System.out.println("Added " + title);
                    } catch (InvalidArgumentException e) {
                        System.out.println("Incorrect arguments, cannot add game.");
                    }
                    break;
                case "EditGame":
                    Long id = Long.parseLong(inputs[1]);
                    Optional<Game> foundGame = gameService.findGameById(id);
                    if (!foundGame.isPresent()) {
                        System.out.println("No game with the given id.");
                        continue;
                    }
                    Game game = foundGame.get();
                    for (int i = 2; i < inputs.length; i++) {
                        String[] pair = inputs[i].split("=");
                        String key = pair[0];
                        String value = pair[1];
                        switch (key) {
                            case "price":
                                game.setPrice(BigDecimal.valueOf(Double.parseDouble(value)));
                                break;
                            case "size":
                                game.setSize(Double.parseDouble(value));
                                break;
                            case "title":
                                game.setTitle(value);
                                break;
                            case "trailer":
                                game.setTrailer(value.substring(value.length() - 11));
                                break;
                            case "thumbnailURL":
                                game.setImageURL(value);
                                break;
                            case "description":
                                game.setDescription(value);
                                break;
                            case "releaseDate":
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                game.setReleaseDate(format.parse(value));
                                break;
                        }
                    }
                    try {
                        gameService.saveUpdatedGame(game);
                        System.out.println("Edited " + game.getTitle());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Invalid game arguments, cannot edit game.");
                    }
                    break;
                case "DeleteGame":
                    Long gameId = Long.parseLong(inputs[1]);
                    gameService.deleteGame(gameId);
                case "AllGame":
                    gameService.printAllGames();
                    break;
                case "DetailGame":
                    String gameTitle = inputs[1];
                    GameDetailsView gameView = gameService.findGameByTitle(gameTitle);
                    if (gameView == null) {
                        System.out.println("No game with this title.");
                    } else {
                        System.out.println("Title: " + gameView.getTitle());
                        System.out.printf("Price: %.2f%n", gameView.getPrice());
                        System.out.println("Description: " + gameView.getDescription());
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        System.out.println("Release date: " + sdf.format(gameView.getReleaseDate()));
                    }
                    break;
                case "OwnedGame":
                    if (loggedUser == null) {
                        System.out.println("No user is logged in.");
                    } else {
                        loggedUser.getGames().forEach(x -> System.out.println(x.getTitle()));
                    }
                    break;
                case "AddItem":
                    if (loggedUser == null) {
                        System.out.println("No user is logged in.");
                        continue;
                    }
                    String itemToAdd = inputs[1];
                    Set<String> alreadyBought = loggedUser.getGames()
                            .stream()
                            .map(Game::getTitle)
                            .collect(Collectors.toSet());
                    if (alreadyBought.contains(itemToAdd)) {
                        System.out.println("You have already bought this game.");
                    } else {
                        shoppingCart.add(itemToAdd);
                        System.out.println(itemToAdd + " added to cart.");
                    }
                    break;
                case "RemoveItem":
                    if (loggedUser == null) {
                        System.out.println("No user is logged in.");
                        continue;
                    }
                    String itemToRemove = inputs[1];
                    if (shoppingCart.contains(itemToRemove)) {
                        shoppingCart.remove(itemToRemove);
                        System.out.println(itemToRemove + " removed from cart.");
                    } else {
                        System.out.println("No such item in the shopping cart to remove.");
                    }
                    break;
                case "BuyItem":
                    if (loggedUser == null) {
                        System.out.println("No user is logged in.");
                    } else {
                        userService.buyGames(loggedUser, shoppingCart);
                        shoppingCart.clear();
                    }
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }
}
