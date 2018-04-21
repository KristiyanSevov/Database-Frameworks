package softuni.bookshop.projections;

import java.math.BigDecimal;

public interface ReducedBook {

    String getTitle();

    String getEditionType();

    String getAgeRestriction();

    BigDecimal getPrice();

}
