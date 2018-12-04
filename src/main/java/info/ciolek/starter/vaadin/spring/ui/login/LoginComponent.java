package info.ciolek.starter.vaadin.spring.ui.login;

import ch.qos.logback.classic.Logger;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("login")
public class LoginComponent extends VerticalLayout implements HasUrlParameter<String> {
    private Logger logger = (Logger) LoggerFactory.getLogger(LoginComponent.class);

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    private Label logoutLabel;

    @Autowired
    private AuthenticationManager authManager;

    public LoginComponent() {
        setAlignItems(Alignment.CENTER);

        logoutLabel = new Label();
        logoutLabel.getElement().getStyle().set("color", "red");
        logoutLabel.setVisible(false);
        add(logoutLabel);

        usernameField = new TextField();
        usernameField.setLabel("Username");

        passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.addKeyPressListener(Key.ENTER, listener -> {
            logger.debug("Enter pressed");
            loginAction();
        });

        loginButton = new Button("Login");
        loginButton.addClickListener(e -> {
            logger.debug("Login button pressed");
            loginAction();
        });

        add(usernameField, passwordField, loginButton);
    }

    private void loginAction() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        loginButton.setEnabled(false);
        if (login(username, password)) {
            loginButton.getUI().ifPresent(ui -> ui.navigate("secure"));
        } else {
            logoutLabel.setVisible(true);
            loginButton.setEnabled(true);
        }
    }

    private boolean login(String username, String password) {
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authenticate.isAuthenticated()) {
                SecurityContext context = SecurityContextHolder.getContext();

                context.setAuthentication(authenticate);
                return true;
            }
        } catch (BadCredentialsException ex) {
            logoutLabel.setText("Incorrect username or password.");
            usernameField.focus();
            passwordField.setValue("");
        } catch (Exception ex) {
            Notification.show("An unexpected error occurred, please try again in a few minutes", 3000, Position.MIDDLE);
            logger.error("Unexpected error while logging in", ex);
        }
        return false;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null && parameter.contentEquals("loggedout")) {
            logoutLabel.setVisible(true);
            logoutLabel.setText("You have been successfully logged out");
        }
    }
}