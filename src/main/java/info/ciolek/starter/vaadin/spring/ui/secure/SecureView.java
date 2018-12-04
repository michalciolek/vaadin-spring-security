package info.ciolek.starter.vaadin.spring.ui.secure;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MenuBarView.class)
public class SecureView extends VerticalLayout {

    public SecureView() {
        setWidth("100%");
        Label text = new Label("You are in the restricted area Home Page.");
        add(text);
    }
}