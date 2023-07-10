package com.project.boardgamerentalfront;

import com.project.boardgamerentalfront.domain.*;
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

         private Grid<Game> grid = new Grid<>(Game.class);
         private Grid<User> userGrid = new Grid<>(User.class);
         private TextField filter = new TextField();

         private GameForm form = new GameForm(this);
         private UserForm userForm = new UserForm(this);
         private Button addNewGame = new Button("Add new game");

         private Button addNewUser = new Button("Add new user");


   public MainView() {
       filter.setPlaceholder("Filter by title...");
       filter.setClearButtonVisible(true);
       filter.setValueChangeMode(ValueChangeMode.EAGER);
       filter.addValueChangeListener(e -> update());
       grid.setColumns("title", "price", "publicationYear", "type");
       HorizontalLayout toolbar = new HorizontalLayout(filter, addNewGame, addNewUser);
       HorizontalLayout mainContent = new HorizontalLayout(grid, form);
       HorizontalLayout userContent = new HorizontalLayout(userGrid, userForm);

       addNewGame.addClickListener(e -> {
           //toolbar.setVisible(true);
           mainContent.setVisible(true);
           userContent.setVisible(false);
           grid.asSingleSelect().clear();
           form.setGame(new Game());
           add(toolbar,mainContent);
       });


       addNewUser.addClickListener(e -> {
          // toolbar.setVisible(true);
           mainContent.setVisible(false);
           userContent.setVisible(true);
           userGrid.setSizeFull();
           userGrid.asSingleSelect().clear();
           userForm.setUser(new User());
           add(toolbar, userContent);
       });



       mainContent.setSizeFull();
       grid.setSizeFull();

       add(toolbar, mainContent);
       form.setGame(null);
       userForm.setUser(null);
       setSizeFull();
       refresh();

       grid.asSingleSelect().addValueChangeListener(event -> form.setGame(grid.asSingleSelect().getValue()));
       userGrid.asSingleSelect().addValueChangeListener(event -> userForm.setUser(userGrid.asSingleSelect().getValue()));

   }

    public void refresh() {
        grid.setItems(gameService.getGames());
        userGrid.setItems(userService.getUsers());
    }
    private void update() {
        grid.setItems(gameService.findByTitle(filter.getValue()));
        userGrid.setItems(userService.findByPhoneNumber(filter.getValue()));
    }

}
