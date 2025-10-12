package com.bank_rest_front.application.view.nav_view;

import com.bank_rest_front.application.view.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Платежи")
@Route(value = "payments", layout = MainLayout.class)
@PermitAll
public class PaymentsView extends VerticalLayout {

    public PaymentsView() {
        setSizeFull();

        add(new H1("Welcome to 'Платежи' page!"));
    }

}
