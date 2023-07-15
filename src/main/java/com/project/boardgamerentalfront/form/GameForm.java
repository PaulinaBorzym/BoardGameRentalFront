package com.project.boardgamerentalfront.form;

import com.project.boardgamerentalfront.MainView;
import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.enums.GameType;
import com.project.boardgamerentalfront.enums.Languages;
import com.project.boardgamerentalfront.service.CurrencyService;
import com.project.boardgamerentalfront.service.DescriptionService;
import com.project.boardgamerentalfront.service.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GameForm extends FormLayout {
    private MainView mainView;
    private TextField title = new TextField("Title");
    private TextArea description = new TextArea("Description");
    private TextField price = new TextField("Price");
    private TextField publicationYear = new TextField("Publication year");
    private ComboBox<GameType> type = new ComboBox<>("Game type");
    private ComboBox<Languages> languages = new ComboBox<>("Language");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Button edit = new Button("Edit");
    private Button changeLanguage = new Button("Change language");
    private GameService service = GameService.getInstance();

    private DescriptionService descriptionService = DescriptionService.getInstance();
    private Binder<Game> binder = new Binder<Game>(Game.class);

    private TextArea descriptionTextArea = new TextArea();

    public GameForm(MainView mainView) {

        type.setItems(GameType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        languages.setItems(Languages.values());
        descriptionTextArea.setReadOnly(true);
        HorizontalLayout descriptionLanguage = new HorizontalLayout(languages, changeLanguage);
        add(title, price, publicationYear, type, description, buttons, descriptionLanguage, descriptionTextArea);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        edit.addClickListener(event -> edit());
        changeLanguage.addClickListener(event -> changeLanguage());

    }

    private void changeLanguage() {
        String language = languages.getValue().toString();
        Game game = binder.getBean();
        String result = descriptionService.changeLanguage(game.getDescription(), language);
        descriptionTextArea.setValue(result);
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
