package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.domain.BookCall;
import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.Rent;
import com.project.boardgamerentalfront.domain.User;
import com.project.boardgamerentalfront.service.BookCallService;
import com.project.boardgamerentalfront.service.GameService;
import com.project.boardgamerentalfront.service.RentService;
import com.project.boardgamerentalfront.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RentForm extends FormLayout {
    private MainView mainView;
    private ComboBox<User> user = new ComboBox<>("Users");
    private ComboBox<Game> game = new ComboBox<>("Game");
    private DatePicker startDate = new DatePicker("Start date");
    private DatePicker endDate = new DatePicker("End date");
    private DatePicker bookDate = new DatePicker("Call date");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private Button accept = new Button("Book a call");
    private RentService service = RentService.getInstance();
    private BookCallService bookCallService = BookCallService.getInstance();
    private UserService userService = UserService.getInstance();
    private GameService gameService = GameService.getInstance();
    private Binder<Rent> binder = new Binder<Rent>(Rent.class);
    private CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();

    public RentForm(MainView mainView) {
        user.setItems(UserService.getInstance().getUsers());
        game.setItems(GameService.getInstance().getGames());
        HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(user, game, startDate, endDate, buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        edit.addClickListener(event -> edit());
        accept.addClickListener(event -> accept());
        checkboxGroup.setLabel("Other options");
        checkboxGroup.setItems("Online rules explanation");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        add(checkboxGroup, bookDate, accept);
    }

    private void accept() {
        BookCall bookCall = new BookCall(bookDate.getValue(), user.getValue().getPhoneNumber(), game.getValue().getTitle());
        bookCallService.save(bookCall);
        mainView.refresh();
        refreshComboBox();
    }

    private void save() {
        Rent rent = binder.getBean();
        Rent newRent = new Rent(rent.getRentId(), rent.getUser(), rent.getGame(), rent.getStartDate(), rent.getEndDate());
        service.save(newRent);
        mainView.refresh();
        setRent(null);
        refreshComboBox();
    }

    private void edit() {
        Rent rent = binder.getBean();
        service.edit(rent);
        mainView.refresh();
        setRent(rent);
        refreshComboBox();
    }

    private void delete() {
        Rent rent = binder.getBean();
        service.delete(rent);
        mainView.refresh();
        setRent(null);
        refreshComboBox();
    }

    public void setRent(Rent rent) {
        binder.setBean(rent);
        if (rent == null) {
            setVisible(false);
        } else {
            setVisible(true);
            user.focus();
        }
        refreshComboBox();
    }

    public void refreshComboBox() {
        user.setItems(userService.getUsers());
        Set<Game> games = gameService.getGames();
        List<Game> rentedGames = service.getRents().stream().map(rent -> rent.getGame()).collect(Collectors.toList());
        List<Game> gameForRent = games.stream().filter(game1 -> !rentedGames.contains(game1)).collect(Collectors.toList());
        game.setItems(gameForRent);
    }
}
