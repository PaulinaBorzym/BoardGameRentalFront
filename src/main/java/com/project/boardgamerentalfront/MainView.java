package com.project.boardgamerentalfront;

import com.project.boardgamerentalfront.domain.Game;
import com.project.boardgamerentalfront.domain.GameForm;
import com.project.boardgamerentalfront.domain.GameService;
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

         private Grid<Game> grid = new Grid<>(Game.class);
         ;
         private TextField filter = new TextField();

         private GameForm form = new GameForm(this);
         private Button addNewGame = new Button("Add new game");


   public MainView() {
       filter.setPlaceholder("Filter by title...");
       filter.setClearButtonVisible(true);
       filter.setValueChangeMode(ValueChangeMode.EAGER);
       filter.addValueChangeListener(e -> update());
       grid.setColumns("title", "price", "publicationYear", "type");

       addNewGame.addClickListener(e -> {
           grid.asSingleSelect().clear();
           form.setGame(new Game());
       });
       HorizontalLayout toolbar = new HorizontalLayout(filter, addNewGame);

       HorizontalLayout mainContent = new HorizontalLayout(grid, form);
       mainContent.setSizeFull();
       grid.setSizeFull();

       add(toolbar, mainContent);
       form.setGame(null);
       setSizeFull();
       refresh();

       grid.asSingleSelect().addValueChangeListener(event -> form.setGame(grid.asSingleSelect().getValue()));

   }

    public void refresh() {
        grid.setItems(gameService.getGames());
    }
    private void update() {
        grid.setItems(gameService.findByTitle(filter.getValue()));
    }

}
