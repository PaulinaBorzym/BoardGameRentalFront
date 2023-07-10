package com.project.boardgamerentalfront.domain;

import com.project.boardgamerentalfront.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GameForm extends FormLayout {
    private MainView mainView;
    private TextField title = new TextField("Title");
    private TextField price = new TextField("Price");
    private TextField publicationYear = new TextField("Publication year");
    private ComboBox<GameType> type = new ComboBox<>("Game type");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button rent = new Button("Rent");
    private GameService service = GameService.getInstance();
    private Binder<Game> binder = new Binder<Game>(Game.class);

    public GameForm(MainView mainView) {
        DatePicker datePicker = new DatePicker("Select date");

        type.setItems(GameType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete, rent);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, price, publicationYear, type, buttons, datePicker);
        binder.bindInstanceFields(this);
        this.mainView = this.mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        rent.addClickListener(event -> rent());
    }

    private void rent() {
    }

    private void save() {
        Game game = binder.getBean();
        service.save(game);
        mainView.refresh();
        setGame(null);
    }

    private void delete() {
        Game game = binder.getBean();
        service.delete(game);
        mainView.refresh();
        setGame(null);
    }

    public void setGame(Game game) {
        binder.setBean(game);

        if (game == null) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }
}
