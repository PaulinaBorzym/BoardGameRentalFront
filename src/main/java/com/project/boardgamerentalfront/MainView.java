package com.project.boardgamerentalfront;

import com.project.boardgamerentalfront.domain.*;
import com.project.boardgamerentalfront.enums.Currency;
import com.project.boardgamerentalfront.form.GameForm;
import com.project.boardgamerentalfront.form.RentForm;
import com.project.boardgamerentalfront.form.StatisticForm;
import com.project.boardgamerentalfront.form.UserForm;
import com.project.boardgamerentalfront.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Route
public class MainView extends VerticalLayout {
    private GameService gameService = GameService.getInstance();
    private UserService userService = UserService.getInstance();
    private RentService rentService = RentService.getInstance();
    private MonthStatisticService monthStatisticService = MonthStatisticService.getInstance();
    private CurrencyService currencyService = CurrencyService.getInstance();
    private BookCallService bookCallService = BookCallService.getInstance();
    private Grid<Game> grid = new Grid<>(Game.class);
    private Grid<User> userGrid = new Grid<>(User.class);
    private Grid<Rent> rentGrid = new Grid<>(Rent.class);
    private Grid<MonthStatistic> monthStatisticGrid = new Grid<>(MonthStatistic.class);
    private Grid<BookCall> bookCallGrid = new Grid<>(BookCall.class);
    private TextField filter = new TextField();
    private GameForm form = new GameForm(this);
    private UserForm userForm = new UserForm(this);
    private RentForm rentForm = new RentForm(this);
    private StatisticForm statisticForm = new StatisticForm(this);
    private Button addNewGame = new Button("Add new game");
    private Button addNewUser = new Button("Add new user");
    private Button addNewRent = new Button("Add new rent");
    private Button statisticsButton = new Button("Statistics");
    private Button monthStatisticButton = new Button("Month Statistics");
    private Button bookCallButton = new Button("Book Calls");
    private ComboBox<Currency> currency = new ComboBox<>("Currency");
    private Button changeCurrency = new Button("Change currency");
    private TextArea infoField = new TextArea();

    @Autowired
    public MainView() {
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "price", "publicationYear", "type");
        monthStatisticGrid.setColumns("month", "year", "numberOfUsers", "numberOfGames",
                "numberOfAllRents", "numberOfLastMonthRents", "amountOfLastMonthEarnedMoney");
        bookCallGrid.setColumns("bookDate", "phoneNumber", "title");
        rentGrid.setColumns("user", "game", "price", "startDate", "endDate");
        currency.setItems(Currency.values());
        infoField.setReadOnly(true);
        infoField.setSizeFull();
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewGame, addNewUser, addNewRent,
                statisticsButton, monthStatisticButton, bookCallButton, currency, changeCurrency);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        HorizontalLayout userContent = new HorizontalLayout(userGrid, userForm);
        HorizontalLayout rentContent = new HorizontalLayout(rentGrid, rentForm);
        HorizontalLayout statisticContent = new HorizontalLayout(statisticForm, infoField);
        HorizontalLayout monthStatisticContent = new HorizontalLayout(monthStatisticGrid);
        HorizontalLayout bookCallContent = new HorizontalLayout(bookCallGrid);

        addNewGame.addClickListener(e -> {
            mainContent.setVisible(true);
            userContent.setVisible(false);
            rentContent.setVisible(false);
            statisticContent.setVisible(false);
            monthStatisticContent.setVisible(false);
            bookCallContent.setVisible(false);
            grid.asSingleSelect().clear();
            form.setGame(new Game());
            add(toolbar, mainContent);
            refresh();
        });

        addNewUser.addClickListener(e -> {
            mainContent.setVisible(false);
            userContent.setVisible(true);
            rentContent.setVisible(false);
            statisticContent.setVisible(false);
            monthStatisticContent.setVisible(false);
            bookCallContent.setVisible(false);
            userGrid.setSizeFull();
            userGrid.asSingleSelect().clear();
            userForm.setUser(new User());
            add(toolbar, userContent);
            refresh();
        });

        addNewRent.addClickListener(e -> {
            rentContent.setVisible(true);
            mainContent.setVisible(false);
            userContent.setVisible(false);
            statisticContent.setVisible(false);
            monthStatisticContent.setVisible(false);
            bookCallContent.setVisible(false);
            rentGrid.setSizeFull();
            rentGrid.asSingleSelect().clear();
            rentForm.setRent(new Rent());
            add(toolbar, rentContent);
            refresh();
        });

        statisticsButton.addClickListener(e -> {
            rentContent.setVisible(false);
            mainContent.setVisible(false);
            userContent.setVisible(false);
            statisticContent.setVisible(true);
            monthStatisticContent.setVisible(false);
            bookCallContent.setVisible(false);
            add(toolbar, statisticContent);
            refresh();
        });

        monthStatisticButton.addClickListener(event -> {
            rentContent.setVisible(false);
            mainContent.setVisible(false);
            userContent.setVisible(false);
            statisticContent.setVisible(false);
            monthStatisticContent.setVisible(true);
            bookCallContent.setVisible(false);
            add(toolbar, monthStatisticContent);
            refresh();
        });

        changeCurrency.addClickListener(e -> {
            currencyService.changeCurrency(currency.getValue().toString());
            refresh();
        });

        bookCallButton.addClickListener(event -> {
            rentContent.setVisible(false);
            mainContent.setVisible(false);
            userContent.setVisible(false);
            statisticContent.setVisible(false);
            monthStatisticContent.setVisible(false);
            bookCallContent.setVisible(true);
            add(toolbar, bookCallContent);
            refresh();
        });

        mainContent.setSizeFull();
        grid.setSizeFull();
        userContent.setSizeFull();
        userGrid.setSizeFull();
        rentContent.setSizeFull();
        rentGrid.setSizeFull();
        statisticContent.setSizeFull();
        monthStatisticGrid.setSizeFull();
        monthStatisticContent.setSizeFull();
        bookCallGrid.setSizeFull();
        ;
        bookCallContent.setSizeFull();

        add(toolbar, mainContent);
        form.setGame(null);
        userForm.setUser(null);
        rentForm.setRent(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setGame(grid.asSingleSelect().getValue()));
        userGrid.asSingleSelect().addValueChangeListener(event -> userForm.setUser(userGrid.asSingleSelect().getValue()));
        rentGrid.asSingleSelect().addValueChangeListener(event -> rentForm.setRent(rentGrid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(gameService.getGames());
        userGrid.setItems(userService.getUsers());
        rentGrid.setItems(rentService.getRents());
        monthStatisticGrid.setItems(monthStatisticService.getMonthStatistic());
        bookCallGrid.setItems(bookCallService.getBookCall());
    }

    public void refreshInfoField(String info) {
        infoField.setValue(info);
    }

    private void update() {
        grid.setItems(gameService.findByTitle(filter.getValue()));
        userGrid.setItems(userService.findByPhoneNumber(filter.getValue()));
        rentGrid.setItems(rentService.findByPhoneNumber(filter.getValue()));
    }

    public String getCurrentCurrency() {
        return (Objects.isNull(currency.getValue())) ? "PLN" : currency.getValue().toString();
    }
}
