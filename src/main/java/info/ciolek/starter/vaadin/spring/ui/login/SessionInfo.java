package info.ciolek.starter.vaadin.spring.ui.login;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionInfo {
    private User user;

    @Nullable
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null && principal instanceof User) {
            user = (User) principal;
        }
        return user;
    }
}