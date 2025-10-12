package com.bank_rest_front.application.view;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@PageTitle("Login | Bank rest")
@Route("login")
@AnonymousAllowed
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm;

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm = new LoginForm();
        loginForm.setAction("login");
        loginForm.getStyle().set("box-shadow", "0 0 4px 0px #0202024a");
        loginForm.getStyle().set("border-radius", "5px");
        loginForm.addForgotPasswordListener(event -> {
            if (getUI().isPresent()) getUI().get().navigate("signup");
        });

        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Bank rest");
        i18nForm.setUsername("Пользователь");
        i18nForm.setPassword("Пароль");
        i18nForm.setForgotPassword("Регистрация");
        i18nForm.setSubmit("Войти");


        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Ошибка авторизации");
        i18nErrorMessage.setMessage("Проверьте правильность ввода логина и пароля");
        i18n.setErrorMessage(i18nErrorMessage);


        i18n.setForm(i18nForm);
        loginForm.setI18n(i18n);

        add(loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error"))
            loginForm.setError(true);
    }
}