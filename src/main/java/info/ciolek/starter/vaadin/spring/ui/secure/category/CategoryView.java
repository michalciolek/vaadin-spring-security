package info.ciolek.starter.vaadin.spring.ui.secure.category;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import info.ciolek.starter.vaadin.spring.ui.secure.MenuBarView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "category", layout = MenuBarView.class)
public class CategoryView extends VerticalLayout {

    public CategoryView(@Autowired CategoryService categoryService) {
        setWidth("100%");
        // TODO use e.g. https://vaadin.com/directory/component/crud-ui-add-on
        Label text = new Label(categoryService.getCategories().toString());
        Button button = new Button("Add category",
                e -> Notification.show(categoryService.addCategory()));

        add(text, button);
    }
}