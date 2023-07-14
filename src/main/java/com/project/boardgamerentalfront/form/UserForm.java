package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.User;
import com.project.boardgamerentalfront.service.UserService;
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
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private UserService service = UserService.getInstance();

    private Binder<User> binder = new Binder<User>(User.class);

    public UserForm(MainView mainView) {
        HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(firstName, lastName,email,phoneNumber, buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        edit.addClickListener(event -> edit());

    }

    private void save() {
        User user = binder.getBean();
        service.save(user);
        mainView.refresh();
        setUser(null);
    }

    private void edit() {
        User user = binder.getBean();
        service.edit(user);
        mainView.refresh();
        setUser(user);
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
    private void delete() {
        User user = binder.getBean();
        service.delete(user);
        mainView.refresh();
        setUser(null);
    }
}
