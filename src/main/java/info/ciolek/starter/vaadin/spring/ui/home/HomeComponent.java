package info.ciolek.starter.vaadin.spring.ui.home;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import info.ciolek.starter.vaadin.spring.ui.login.LoginComponent;

@Route("")
public class HomeComponent extends VerticalLayout {

    public HomeComponent() {
        Label greeting = new Label("Hello anonymous!");
        add(greeting);
        add(new RouterLink("Go to login page", LoginComponent.class));
    }
}
