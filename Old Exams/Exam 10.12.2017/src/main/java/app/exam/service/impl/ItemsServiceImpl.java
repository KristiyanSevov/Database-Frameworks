package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    private final Validator validator;
    private final ModelParser mapper;
    private final ItemsRepository itemsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemsServiceImpl(Validator validator,
                            ModelParser mapper, ItemsRepository itemsRepository,
                            CategoryRepository categoryRepository) {
        this.validator = validator;
        this.mapper = mapper;
        this.itemsRepository = itemsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(ItemJSONImportDTO model) {
        if (validator.validate(model).size() != 0){
            throw new IllegalArgumentException();
        }
        Item item = itemsRepository.findByName(model.getName());
        if (item != null) {
            throw new IllegalArgumentException();
        }
        Category category = categoryRepository.findByName(model.getCategory());
        if (category == null){
            category = new Category();
            category.setName(model.getCategory());
            categoryRepository.saveAndFlush(category);
        }
        Item savedItem = mapper.convert(model, Item.class);
        savedItem.setCategory(category);
        itemsRepository.saveAndFlush(savedItem);
    }
}
