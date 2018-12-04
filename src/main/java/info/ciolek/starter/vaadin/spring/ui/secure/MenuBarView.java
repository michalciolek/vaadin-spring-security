package info.ciolek.starter.vaadin.spring.ui.secure;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import info.ciolek.starter.vaadin.spring.ui.secure.book.BookView;
import info.ciolek.starter.vaadin.spring.ui.secure.category.CategoryView;

@ParentLayout(SecureComponent.class)
public class MenuBarView extends HorizontalLayout implements RouterLayout {
    private VerticalLayout menu;

    public MenuBarView() {
        setSizeFull();
        menu = new VerticalLayout();
        menu.setSizeUndefined();
        menu.setSpacing(false);
        add(menu);

        addMenuElement(SecureView.class, "Secure", VaadinIcon.LOCK);
        addMenuElement(BookView.class, "Books", VaadinIcon.BOOK);
        addMenuElement(CategoryView.class, "Categories", VaadinIcon.CAR);
    }

    private void addMenuElement(Class<? extends Component> navigationTarget,
                                String name, VaadinIcon icon) {
        Button button = new Button(name, new Icon(icon));
        button.addClickListener(e -> {
            button.getUI().ifPresent(ui -> ui.navigate(navigationTarget));
        });
        menu.add(button);
    }

    protected void onAttach(AttachEvent attachEvent) {
        UI ui = getUI().get();
        Button button = new Button("Logout", event -> {
            // Redirect this page immediately
            ui.getPage().executeJavaScript(
                    "window.location.href='/logout'");

            // Close the session
            ui.getSession().close();
        });

        button.setIcon(new Icon(VaadinIcon.EXIT));
        menu.add(button);

        // Notice quickly if other UIs are closed
        ui.setPollInterval(3000);
    }

}