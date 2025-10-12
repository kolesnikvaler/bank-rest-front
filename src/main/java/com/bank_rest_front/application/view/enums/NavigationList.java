package com.bank_rest_front.application.view.enums;

import com.bank_rest_front.application.utils.SecurityUtils;
import com.bank_rest_front.application.view.nav_view.CreateCardApplicationsView;
import com.bank_rest_front.application.view.nav_view.PaymentsView;
import com.bank_rest_front.application.view.nav_view.StartPageView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class NavigationList extends ArrayList<HorizontalLayout> {
    private final UserDetails user;

    public NavigationList(UserDetails user) {
        this.user = user;
        add(HOME, "Главная", StartPageView.class);
        add(MONEY_EXCHANGE, "Платежи", PaymentsView.class);
        add(CREDIT_CARD, "Заявки на создание карт", CreateCardApplicationsView.class);
    }

    private void add(VaadinIcon icon, String label, Class<? extends Component> clazz) {
        HorizontalLayout link = new HorizontalLayout(icon.create(), new RouterLink(label, clazz));

        if (clazz.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAllowed = clazz.getAnnotation(RolesAllowed.class);
            if (SecurityUtils.hasAnyRole(user, rolesAllowed.value()))
                add(link);
        } else
            add(link);
    }
}
