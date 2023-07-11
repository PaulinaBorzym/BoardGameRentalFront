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
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class RentForm extends FormLayout {
    private MainView mainView;
    private ComboBox<User> users = new ComboBox<>("Users");
    private ComboBox<Game> games = new ComboBox<>("Game");
    DatePicker datePickerStart = new DatePicker("Start date");
    DatePicker datePickerEnd = new DatePicker("End date");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private RentService service = RentService.getInstance();
   // private Binder<Rent> binder = new Binder<Rent>(Rent.class);


    public RentForm (MainView mainView){
        users.setItems(UserService.getInstance().getUsers());
        games.setItems(GameService.getInstance().getGames());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(users,games,datePickerStart,datePickerEnd,buttons);
        //binder.bindInstanceFields(this);
        this.mainView = this.mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        //Rent rent = binder.getBean();
        //service.save(rent);
        mainView.refresh();
        setRent(null);
    }

    private void delete() {
        //Rent rent = binder.getBean();
        //service.delete(rent);
        mainView.refresh();
        setRent(null);
    }

    public void setRent(Rent rent) {
        //binder.setBean(rent);

        if (rent == null) {
            setVisible(false);
        } else {
            setVisible(true);
            users.focus();
        }
    }
}
