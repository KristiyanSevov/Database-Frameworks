package productshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.dto.binding.UserCreateBindingModel;
import productshop.dto.binding.UsersWrapper;
import productshop.dto.views.soldProducts.ProductSoldView;
import productshop.dto.views.soldProducts.UserWithSoldProductsView;
import productshop.dto.views.usersAndProducts.ProductView;
import productshop.dto.views.usersAndProducts.ProductsView;
import productshop.dto.views.usersAndProducts.UserProductsView;
import productshop.dto.views.usersAndProducts.UsersAndProductsView;
import productshop.entities.User;
import productshop.parsers.ModelParser;
import productshop.repositories.UserRepository;
import productshop.services.api.UserService;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelParser mapper;
    private final UserRepository userRepository;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(ModelParser mapper, UserRepository userRepository, Validator validator) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public void saveUsers(UsersWrapper userModels) {
        for (UserCreateBindingModel model : userModels.getUsers()) {
            if (validator.validate(model).size() == 0) {
                User user = mapper.convert(model, User.class);
                userRepository.save(user);
            }
        }
    }

    @Override
    public List<UserWithSoldProductsView> findUsersWithSoldProducts() {
        return userRepository.findUsersWithSoldProducts()
                .stream()
                .map(u -> new UserWithSoldProductsView(u.getFirstName(), u.getLastName(),
                        u.getSoldProducts()
                                .stream()
                                .filter(p -> p.getBuyer() != null)
                                .map(p -> new ProductSoldView(p.getName(), p.getPrice(),
                                        p.getBuyer().getFirstName(), p.getBuyer().getLastName()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public UsersAndProductsView getUsersAndProducts() {
        Set<User> usersWithSoldProducts = userRepository.findUsersWithSoldProducts();
        return new UsersAndProductsView(usersWithSoldProducts.size(),
                usersWithSoldProducts
                        .stream()
                        .map(u -> new UserProductsView(u.getFirstName(), u.getLastName(), u.getAge(),
                                new ProductsView(
                                        u.getSoldProducts().stream().filter(x -> x.getBuyer() != null).count(),
                                        u.getSoldProducts().stream()
                                                .filter(x -> x.getBuyer() != null)
                                                .map(p -> new ProductView(p.getName(), p.getPrice()))
                                                .collect(Collectors.toList()))))
                        .sorted(Comparator.comparing((UserProductsView x) -> -x.getSoldProducts().getProducts().size())
                                .thenComparing(UserProductsView::getLastName)) //this is sorted from query anyway
                        .collect(Collectors.toList()));
    }
}
