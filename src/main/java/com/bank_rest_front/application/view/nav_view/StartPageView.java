package com.bank_rest_front.application.view.nav_view;

import com.bank_rest_front.application.view.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;

@PageTitle("Главная")
@Route(value = "start", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class StartPageView extends VerticalLayout {

    public StartPageView() {
        setSizeFull();

        add(new H1("Welcome to start page!"));
    }

}
