package info.ciolek.starter.vaadin.spring.ui.secure.book;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import info.ciolek.starter.vaadin.spring.ui.secure.MenuBarView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "book", layout = MenuBarView.class)
public class BookView extends VerticalLayout {

    public BookView(@Autowired BookService bookService) {
        setWidth("100%");
        // TODO use e.g. https://vaadin.com/directory/component/crud-ui-add-on
        Label text = new Label(bookService.getBooks().toString());
        Button button = new Button("Add book",
                e -> Notification.show(bookService.addBook()));

        add(text, button);
    }
}