package com.bank_rest_front.application.view.nav_view;

import com.bank_rest_front.application.view.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Заявки на создание карт")
@Route(value = "create_card_apps", layout = MainLayout.class)
@RolesAllowed({"ADMIN"})
public class CreateCardApplicationsView extends VerticalLayout {

    public CreateCardApplicationsView() {
        setSizeFull();

        add(new H1("Welcome to 'Заявки на создание карт' page!"));
    }

}
