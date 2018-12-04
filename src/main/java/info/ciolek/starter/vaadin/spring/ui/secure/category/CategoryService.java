package info.ciolek.starter.vaadin.spring.ui.secure.category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>(Arrays.asList(
            new Category("Science fiction"),
            new Category("Thriller")
    ));

    public List<Category> getCategories() {
        return categories;
    }

    public String addCategory() {
        return "TODO addCategory()";
    }
}
