package com.bank_rest_front.application.view.nav_view;

import com.bank_rest_front.application.entity.User;
import com.bank_rest_front.application.service.BankUserService;
import com.bank_rest_front.application.view.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import static com.vaadin.flow.component.grid.GridVariant.LUMO_ROW_STRIPES;

@PageTitle("Пользователи")
@Route(value = "users", layout = MainLayout.class)
@RolesAllowed({"ADMIN"})
public class UserManagementView extends VerticalLayout {

    public UserManagementView(BankUserService userService) {
        setSizeFull();

        Grid<User> grid = new Grid<>();
        grid.setSizeFull();
        grid.addThemeVariants(LUMO_ROW_STRIPES);
        grid.addColumn(new ComponentRenderer<>(user -> new NativeLabel(user.getLastName())));
        grid.addColumn(new ComponentRenderer<>(user -> new NativeLabel(user.getFirstName())));
        grid.addColumn(new ComponentRenderer<>(user -> new NativeLabel(user.getUsername())));

        grid.setItems(userService.getBankUsers(MainLayout.getCurrentUser()));

        add(
                new H1("Пользователи"),
                grid
        );
    }

}

