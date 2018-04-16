package productshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.dto.binding.ProductCreateBindingModel;
import productshop.dto.views.ProductsInRangeView;
import productshop.entities.Category;
import productshop.entities.Product;
import productshop.entities.User;
import productshop.parsers.ModelParser;
import productshop.repositories.CategoryRepository;
import productshop.repositories.ProductRepository;
import productshop.repositories.UserRepository;
import productshop.services.api.ProductService;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelParser mapper;
    private final Validator validator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository,
                              ModelParser mapper,
                              Validator validator) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void saveProducts(ProductCreateBindingModel[] productModels) {
        Random random = new Random();
        for (ProductCreateBindingModel model : productModels) {
            if (validator.validate(model).size() == 0) {
                Product product = mapper.convert(model, Product.class);
                long userCount = userRepository.count();
                product.setSeller(userRepository.findById((long) random.nextInt((int) userCount) + 1).get());
                if (random.nextDouble() < 0.8) { // leave 20% with no buyer
                    product.setBuyer(userRepository.findById((long) random.nextInt((int) userCount) + 1).get());
                }
                productRepository.save(product);
            }
        }
    }

    @Override
    public void generateCategories() {
        Random random = new Random();
        List<Product> products = productRepository.findAll();
        long categoryCount = categoryRepository.count();
        for (Product product : products) {
            Category category = categoryRepository.findById((long) random.nextInt((int) categoryCount) + 1).get();
            category.getProducts().add(product);
            categoryRepository.save(category); //many-to-many owner is Category entity so we save that one
        }
    }

    @Override
    public List<ProductsInRangeView> findProductsInRange(int from, int to) {
        return productRepository.findProductsInRange(BigDecimal.valueOf(from), BigDecimal.valueOf(to))
                .stream()
                .map(p -> new ProductsInRangeView(
                        p.getName(),
                        p.getPrice(),
                        p.getSeller().getFirstName() != null ?
                                p.getSeller().getFirstName() + " " + p.getSeller().getLastName() :
                                p.getSeller().getLastName()))
                .collect(Collectors.toList());
    }
}
