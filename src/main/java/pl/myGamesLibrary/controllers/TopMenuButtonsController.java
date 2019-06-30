package pl.myGamesLibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    public static final String LIBRARY_FXML = "/fxml/Library.fxml";
    public static final String LIST_GAMES_FXML = "/fxml/GamesList.fxml";
    public static final String STATISTICS_FXML = "/fxml/Statistics.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddGame.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup tougleButtons;

    @FXML
    public void openLibrary( ) {
        mainController.setCenter(LIBRARY_FXML);
    }

    @FXML
    public void openListGames( ) {
        mainController.setCenter(LIST_GAMES_FXML);
    }

    @FXML
    public void openStatistics( ) {
        mainController.setCenter(STATISTICS_FXML);
    }

    @FXML
    public void addGame() {
        resetToggleButtons();

        mainController.setCenter(ADD_BOOK_FXML);
    }

    @FXML
    public void addCategory() {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGORY_FXML);
    }

    @FXML
    public void addAuthor() {
        resetToggleButtons();
        mainController.setCenter(ADD_AUTHOR_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void resetToggleButtons() {
        if (tougleButtons.getSelectedToggle() != null) {
            tougleButtons.getSelectedToggle().setSelected(false);
        }
    }

}
