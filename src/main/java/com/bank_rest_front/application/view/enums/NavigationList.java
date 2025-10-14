package com.bank_rest_front.application.view.enums;

import com.bank_rest_front.application.utils.SecurityUtils;
import com.bank_rest_front.application.view.nav_view.CreateCardApplicationsView;
import com.bank_rest_front.application.view.nav_view.PaymentsView;
import com.bank_rest_front.application.view.nav_view.StartPageView;
import com.bank_rest_front.application.view.nav_view.UserManagementView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class NavigationList extends ArrayList<HorizontalLayout> {
    private final UserDetails user;

    public NavigationList(UserDetails user) {
        this.user = user;
        add(HOME, StartPageView.class);
        add(MONEY_EXCHANGE, PaymentsView.class);
        add(CREDIT_CARD, CreateCardApplicationsView.class);
        add(USER, UserManagementView.class);
    }

    private void add(VaadinIcon icon, Class<? extends Component> clazz) {
        HorizontalLayout link = new HorizontalLayout(icon.create(), new RouterLink(getPageLabel(clazz), clazz));

        if (clazz.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAllowed = clazz.getAnnotation(RolesAllowed.class);
            if (SecurityUtils.hasAnyRole(user, rolesAllowed.value()))
                add(link);
        } else
            add(link);
    }

    private String getPageLabel(Class<? extends Component> clazz) {
        if (clazz.isAnnotationPresent(PageTitle.class)) {
            PageTitle pageTitle = clazz.getAnnotation(PageTitle.class);
            return pageTitle.value();
        } else
            return "Unknown";
    }
}
