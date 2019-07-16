package pl.myGamesLibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import pl.myGamesLibrary.database.models.User;

public class TopMenuButtonsController {

    public static final String MY_GAMES_LIBRARY_FXML = "/fxml/MyGamesLibrary.fxml";
    public static final String LIST_GAMES_FXML = "/fxml/GamesList.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddGame.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup tougleButtons;

    @FXML
    public void openMyGamesLibrary( ) {
        this.mainController.setCenter(MY_GAMES_LIBRARY_FXML);
    }

    @FXML
    public void openListGames( ) {
        this.mainController.setCenter(LIST_GAMES_FXML);
    }

    @FXML
    public void addGame() {
        this.mainController.setCenter(ADD_BOOK_FXML);
    }

    @FXML
    public void addCategory() {
        this.mainController.setCenter(ADD_CATEGORY_FXML);
    }

    @FXML
    public void addAuthor() {
        this.mainController.setCenter(ADD_AUTHOR_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
