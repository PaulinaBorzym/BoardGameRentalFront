package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.Rent;
import com.project.boardgamerentalfront.domain.User;
import com.project.boardgamerentalfront.enums.GameType;
import com.project.boardgamerentalfront.service.GameService;
import com.project.boardgamerentalfront.service.RentService;
import com.project.boardgamerentalfront.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.Converter;

import java.time.LocalDate;

public class RentForm extends FormLayout {
    private MainView mainView;
    private ComboBox<User> user = new ComboBox<>("Users");
    private ComboBox<Game> game = new ComboBox<>("Game");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End date");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private RentService service = RentService.getInstance();
    private Binder<Rent> binder = new Binder<Rent>(Rent.class);
    private CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();



    public RentForm (MainView mainView){
        user.setItems(UserService.getInstance().getUsers());
        game.setItems(GameService.getInstance().getGames());
        HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(user,game,startDate,endDate,buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        edit.addClickListener(event -> edit());
        checkboxGroup.setLabel("Other options");
        checkboxGroup.setItems("Online rules explanation", "Send the necessary things for the game (notebook, pen)");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        add(checkboxGroup);
    }

    private void save() {
        Rent rent = binder.getBean();
        Rent newRent = new Rent(rent.getUser(),rent.getGame(),rent.getStartDate(),rent.getEndDate());
        service.save(newRent);
        mainView.refresh();
        setRent(null);
    }
    private void edit() {
        Rent rent = binder.getBean();
        service.save(rent);
        mainView.refresh();
        setRent(rent);
    }

    private void delete() {
        Rent rent = binder.getBean();
        service.delete(rent);
        mainView.refresh();
        setRent(null);
    }

    public void setRent(Rent rent) {
        binder.setBean(rent);

        if (rent == null) {
            setVisible(false);
        } else {
            setVisible(true);
            user.focus();
        }
    }
}
