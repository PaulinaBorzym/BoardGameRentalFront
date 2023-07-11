package com.project.boardgamerentalfront;

import com.project.boardgamerentalfront.domain.*;
import com.project.boardgamerentalfront.form.GameForm;
import com.project.boardgamerentalfront.form.RentForm;
import com.project.boardgamerentalfront.form.UserForm;
import com.project.boardgamerentalfront.service.GameService;
import com.project.boardgamerentalfront.service.RentService;
import com.project.boardgamerentalfront.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;



@Route
 public class MainView extends VerticalLayout {

         private GameService gameService = GameService.getInstance();

         private UserService userService = UserService.getInstance();
         private RentService rentService = RentService.getInstance();

         private Grid<Game> grid = new Grid<>(Game.class);
         private Grid<User> userGrid = new Grid<>(User.class);
         private Grid<Rent> rentGrid = new Grid<>(Rent.class);
         private TextField filter = new TextField();

         private GameForm form = new GameForm(this);
         private UserForm userForm = new UserForm(this);
         private RentForm rentForm = new RentForm(this);
         private Button addNewGame = new Button("Add new game");

         private Button addNewUser = new Button("Add new user");

         private Button addNewRent = new Button("Add new rent");


   public MainView() {
       filter.setPlaceholder("Filter by title...");
       filter.setClearButtonVisible(true);
       filter.setValueChangeMode(ValueChangeMode.EAGER);
       filter.addValueChangeListener(e -> update());
       grid.setColumns("title", "price", "publicationYear", "type");
       HorizontalLayout toolbar = new HorizontalLayout(filter, addNewGame, addNewUser, addNewRent);
       HorizontalLayout mainContent = new HorizontalLayout(grid, form);
       HorizontalLayout userContent = new HorizontalLayout(userGrid, userForm);
       HorizontalLayout rentContent = new HorizontalLayout(rentGrid,rentForm);

       addNewGame.addClickListener(e -> {
           //toolbar.setVisible(true);
           mainContent.setVisible(true);
           userContent.setVisible(false);
           rentContent.setVisible(false);
           grid.asSingleSelect().clear();
           form.setGame(new Game());
           add(toolbar,mainContent);
       });


       addNewUser.addClickListener(e -> {
          // toolbar.setVisible(true);
           mainContent.setVisible(false);
           userContent.setVisible(true);
           rentContent.setVisible(false);
           userGrid.setSizeFull();
           userGrid.asSingleSelect().clear();
           userForm.setUser(new User());
           add(toolbar, userContent);
       });

       addNewRent.addClickListener(e -> {
           // toolbar.setVisible(true);
           rentContent.setVisible(true);
           mainContent.setVisible(false);
           userContent.setVisible(false);
           rentGrid.setSizeFull();
           rentGrid.asSingleSelect().clear();
           rentForm.setRent(new Rent());
           add(toolbar, rentContent);
       });



       mainContent.setSizeFull();
       grid.setSizeFull();
       userContent.setSizeFull();
       userGrid.setSizeFull();
       rentContent.setSizeFull();
       rentGrid.setSizeFull();

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
    }
    private void update() {
        grid.setItems(gameService.findByTitle(filter.getValue()));
        userGrid.setItems(userService.findByPhoneNumber(filter.getValue()));
        rentGrid.setItems(rentService.findByPhoneNumber(filter.getValue()));
    }

}
