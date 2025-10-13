package com.bank_rest_front.application.view.register;

import com.bank_rest_front.application.utils.AppUrls;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;
import java.util.stream.Stream;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

public class RegistrationForm extends FormLayout {
    private final TextField firstNameField = new TextField("First name");
    private final TextField lastNameField = new TextField("Last name");
    private final EmailField emailField = new EmailField("Email");
    private final PasswordField passwordField = new PasswordField("Password");
    private final PasswordField confirmPasswordField = new PasswordField("Confirm password");

    public RegistrationForm(RestTemplate restTemplate) {
        addClassName("registration-form");
        setWidth("300px");
        setHeight("fit-content");
        getStyle().set("box-shadow", "0 0 4px 0px #0202024a");
        getStyle().set("border-radius", "5px");
        getStyle().set("padding", "10px");
        getStyle().set("background", "#80808017");

        Button submit = new Button("Submit");
        submit.addClickListener(click -> validateAndSaveUser(restTemplate));
        submit.addClickShortcut(Key.ENTER);

        setRequiredIndicatorVisible(firstNameField, lastNameField, emailField, passwordField, confirmPasswordField);

        add(
                new H3("Sign up form!"),
                firstNameField,
                lastNameField,
                emailField,
                passwordField,
                confirmPasswordField,
                submit
        );
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    private boolean checkComponents(HasValueAndElement<?, ?>... components) {
        for (HasValueAndElement<?, ?> component : components) {
            if (component.getValue() == null) return false;
        }
        return true;
    }

    private void validateAndSaveUser(RestTemplate restTemplate) {
        if (checkComponents(
                firstNameField,
                lastNameField,
                emailField,
                passwordField,
                confirmPasswordField
        )) {
            if (!Objects.equals(passwordField.getValue(), confirmPasswordField.getValue())) {
                new Notification("Passwords are not equal!", 3000, TOP_CENTER).open();
                return;
            }
            UserCreateRequest userCreateRequest = new UserCreateRequest(
                    firstNameField.getValue(),
                    lastNameField.getValue(),
                    emailField.getValue(),
                    passwordField.getValue()
            );

            // Создаём RequestEntity
            RequestEntity<UserCreateRequest> request = RequestEntity
                    .post(URI.create(AppUrls.bankAppUrl + "/auth/register"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userCreateRequest);

            try {
                // Отправляем запрос
                ResponseEntity<String> response = restTemplate.exchange(request, String.class);

                if (!response.getStatusCode().is2xxSuccessful()) {
                    new Notification("User was not created", 2000, TOP_CENTER).open();
                    return;
                }
                new Notification("User %s was created".formatted(emailField.getValue()), 2000, TOP_CENTER).open();
                fireEvent(new SaveEvent(this));
            } catch (RestClientException e) {
                new Notification(e.getMessage(), 2000, TOP_CENTER).open();
            }
        } else
            new Notification("All fields must be filled", 2000, TOP_CENTER).open();
    }

    private record UserCreateRequest(String firstName, String lastName, String email, String password) {}

    private static abstract class RegistrationFormEvent extends ComponentEvent<RegistrationForm> {

        public RegistrationFormEvent(RegistrationForm source) {
            super(source, false);
        }
    }

    public static class SaveEvent extends RegistrationFormEvent {
        public SaveEvent(RegistrationForm source) {
            super(source);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
}
