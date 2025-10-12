package com.bank_rest_front.application.view.register;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Route("signup")
@PageTitle("Регистрация")
@AnonymousAllowed
public class RegistrationView extends VerticalLayout {

    public RegistrationView(RestTemplate restTemplate) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        RegistrationForm form = new RegistrationForm(restTemplate);
        form.addSaveListener(saveEvent -> {
            if (getUI().isPresent()) getUI().get().navigate("login");
        });
        form.getStyle().set("align-self", "center");
        add(form);
    }
}