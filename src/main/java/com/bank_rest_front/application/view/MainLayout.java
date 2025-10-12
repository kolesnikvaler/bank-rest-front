package com.bank_rest_front.application.view;

import com.bank_rest_front.application.security.SecurityService;
import com.bank_rest_front.application.view.enums.NavigationList;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

@org.springframework.stereotype.Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        configureMainLayout();
    }

    private void configureMainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Button logout = new Button("Logout", e -> securityService.logout());
        logout.getStyle().set("margin-top", "0px");
        logout.getStyle().set("margin-bottom", "0px");

        DrawerToggle toggle = new DrawerToggle();
        toggle.getStyle().set("margin-top", "0px");
        toggle.getStyle().set("margin-bottom", "0px");
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        Div div = new Div();
        div.setWidthFull();

        HorizontalLayout header = new HorizontalLayout(toggle, div, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM,
                LumoUtility.Margin.Vertical.NONE
        );

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new DrawerContent(securityService.getAuthenticatedUser()));
    }

    static class DrawerContent extends com.vaadin.flow.component.html.Section {
        public DrawerContent(UserDetails user) {
            addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");

            H1 appName = new H1("Bank Rest");
            appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");
            appName.getStyle().set("font-size", "20px");
            appName.getStyle().set("font-weight", "700");

            Nav nav = new Nav();
            nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
            nav.getStyle().set("padding", "0 20px");
            nav.getStyle().set("display", "flex");
            nav.getStyle().set("flex-direction", "column");
            nav.getStyle().set("gap", "10px");

            new NavigationList(user).forEach(nav::add);

            Footer footer = new Footer();
            footer.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

            add(appName, nav, footer);
        }
    }
}
