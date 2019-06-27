package pl.myGamesLibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.myGamesLibrary.modelFx.AuthorFx;
import pl.myGamesLibrary.modelFx.CategoryFx;
import pl.myGamesLibrary.modelFx.GameModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

public class GameController {

    @FXML
    private TextField titleTextField;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    private TextArea descTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    public Button addGameButton;
    @FXML
    public Button clearControlsButton;

    private GameModel gameModel;


    @FXML
    public void initialize(){
        this.gameModel = new GameModel();

        try {
            this.gameModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        bindings();
    }

    private void bindings() {
        this.categoryComboBox.setItems(this.gameModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.gameModel.getAuthorFxObservableList());

        this.gameModel.getGameFxObjectProperty().titleProperty().bind(this.titleTextField.textProperty());
        this.gameModel.getGameFxObjectProperty().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
        this.gameModel.getGameFxObjectProperty().authorFxProperty().bind(this.authorComboBox.valueProperty());
        this.gameModel.getGameFxObjectProperty().descriptionProperty().bind(this.descTextArea.textProperty());
        this.gameModel.getGameFxObjectProperty().ratingProperty().bind(this.ratingSlider.valueProperty());
        this.gameModel.getGameFxObjectProperty().releaseDateProperty().bind(this.releaseDatePicker.valueProperty());

        this.addGameButton.disableProperty().bind(titleTextField.textProperty().isEmpty()
                .or(this.gameModel.getGameFxObjectProperty().categoryFxProperty().isNull())
                .or(this.gameModel.getGameFxObjectProperty().authorFxProperty().isNull())
                .or(this.gameModel.getGameFxObjectProperty().descriptionProperty().isEmpty())
                .or(this.gameModel.getGameFxObjectProperty().releaseDateProperty().isNull()));

        this.clearControlsButton.disableProperty().bind(titleTextField.textProperty().isEmpty()
                .and(this.gameModel.getGameFxObjectProperty().categoryFxProperty().isNull())
                .and(this.gameModel.getGameFxObjectProperty().authorFxProperty().isNull())
                .and(this.gameModel.getGameFxObjectProperty().descriptionProperty().isEmpty())
                .and(this.gameModel.getGameFxObjectProperty().releaseDateProperty().isNull()));
    }

    @FXML
    public void addGameOnAction() {
        try {
            this.gameModel.saveGameInDataBase();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        clearControls();
    }

    public void clearControlsOnAction() {
        clearControls();
    }

    private void clearControls(){
        this.titleTextField.clear();
        this.categoryComboBox.setValue(null);
        this.authorComboBox.setValue(null);
        this.descTextArea.clear();
        this.ratingSlider.setValue(1);
        this.releaseDatePicker.setValue(null);
    }
}
