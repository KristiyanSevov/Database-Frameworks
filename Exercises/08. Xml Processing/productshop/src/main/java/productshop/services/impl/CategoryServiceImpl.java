package productshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.dto.binding.CategoriesWrapper;
import productshop.dto.binding.CategoryCreateBindingModel;
import productshop.dto.views.categories.CategoriesByCountView;
import productshop.entities.Category;
import productshop.parsers.ModelParser;
import productshop.repositories.CategoryRepository;
import productshop.services.api.CategoryService;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelParser mapper;
    private final Validator validator;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelParser mapper, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void saveProducts(CategoriesWrapper categoryModels) {
        for (CategoryCreateBindingModel model : categoryModels.getCategories()) {
            if (validator.validate(model).size() == 0) {
                Category category = mapper.convert(model, Category.class);
                categoryRepository.save(category);
            }
        }
    }

    @Override
    public List<CategoriesByCountView> findCategoriesWithProductCount() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoriesByCountView(c.getName(), c.getProducts().size(),
                        c.getProducts().size() == 0 ? 0 : //check for no products case because of average()
                                c.getProducts().stream()
                                        .mapToDouble(p -> p.getPrice().doubleValue()).average().getAsDouble(),
                c.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum()))
                .sorted(Comparator.comparingInt(x -> -x.getProductsCount()))
                .collect(Collectors.toList());
    }
}
