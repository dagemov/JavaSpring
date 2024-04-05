package DagemovBackend.DaemovBackendEcommer.dataTest;

import DagemovBackend.DaemovBackendEcommer.domain.model.Category;

import java.time.LocalDateTime;
import java.util.List;

public class TestCategoryData {
    public static List<Category> createCategory() {
        Category category = new Category(10L, "Category Test","Description Unit Test", LocalDateTime.now(),null);
        Category category1 = new Category(20L, "Tag Category Test","Tag Description Unit Test", LocalDateTime.now(),null);
        Category category2 = new Category(30L, "Shoes Category Test","Shoes Description Unit Test", LocalDateTime.now(),null);
        Category category3 = new Category(40L, "Cream Category Test","Cream Description Unit Test", LocalDateTime.now(),null);
        List<Category> categories = List.of(category,category1,category2,category3);
        return categories;
    }
}
