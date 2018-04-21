package app.exam.service.impl;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Item;
import app.exam.domain.entities.OrderItem;
import app.exam.repository.CategoryRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        List<CategoryExportDTO> categories = categoryRepository.findAll()
                .stream()
                .filter(c -> categoryNames.contains(c.getName()))
                .map(c -> {
                    Item mostPopularItem = c.getItems()
                            .stream()
                            .sorted(Comparator.comparing(
                                    (Item i) -> i.getOrderItems()
                                            .stream()
                                            .mapToDouble(oi -> oi.getQuantity() * i.getPrice().doubleValue())
                                            .sum(),
                                    Comparator.reverseOrder()))
                            .findFirst()
                            .get();
                    return new CategoryExportDTO(c.getName(),
                            new MostPopularItemDTO(
                                    mostPopularItem.getName(),
                                    mostPopularItem.getOrderItems()
                                            .stream()
                                            .mapToDouble(oi ->
                                                    oi.getQuantity() * mostPopularItem.getPrice().doubleValue())
                                            .sum(),
                                    mostPopularItem.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum()));
                })
                .sorted(Comparator.comparing((CategoryExportDTO c) -> -c.getItem().getTotalMade())
                        .thenComparing(c -> -c.getItem().getTimesSold()))
                .collect(Collectors.toList());
        return new CategoriesFrequentItemsXMLExportDTO(categories);
    }
}
