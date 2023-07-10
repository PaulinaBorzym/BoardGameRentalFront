package com.project.boardgamerentalfront.domain;

import com.project.boardgamerentalfront.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {

    private MainView mainView;
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");
    private TextField phoneNumber = new TextField("Phone number");
    private Button save = new Button("Save");
    private UserService service = UserService.getInstance();

    private Binder<User> binder = new Binder<User>(User.class);

    public UserForm(MainView mainView) {
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(firstName, lastName,email,phoneNumber, buttons);
        binder.bindInstanceFields(this);
        this.mainView = this.mainView;
        save.addClickListener(event -> save());

    }

    private void save() {
        User user = binder.getBean();
        service.save(user);
        mainView.refresh();
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            phoneNumber.focus();
        }
    }
}
