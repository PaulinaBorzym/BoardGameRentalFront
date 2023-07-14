package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.enums.GameType;
import com.project.boardgamerentalfront.service.GameService;
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
    private Button edit = new Button("Edit");
    private GameService service = GameService.getInstance();
    private Binder<Game> binder = new Binder<Game>(Game.class);

    public GameForm(MainView mainView) {

        type.setItems(GameType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, price, publicationYear, type, buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        edit.addClickListener(event -> edit());
    }

    private void save() {
        Game game = binder.getBean();
        service.save(game);
        mainView.refresh();
        setGame(null);
    }

    private void edit() {
        Game game = binder.getBean();
        service.edit(game);
        mainView.refresh();
        setGame(game);
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
