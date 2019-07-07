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
        validation();
    }

    public void bindings() {
        this.categoryComboBox.setItems(this.gameModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.gameModel.getAuthorFxObservableList());

//        this.gameModel.getGameFxObjectProperty().titleProperty().bind(this.titleTextField.textProperty());
//        this.gameModel.getGameFxObjectProperty().categoryFxProperty().bind(this.categoryComboBox.valueProperty());
//        this.gameModel.getGameFxObjectProperty().authorFxProperty().bind(this.authorComboBox.valueProperty());
//        this.gameModel.getGameFxObjectProperty().descriptionProperty().bind(this.descTextArea.textProperty());
//        this.gameModel.getGameFxObjectProperty().ratingProperty().bind(this.ratingSlider.valueProperty());
//        this.gameModel.getGameFxObjectProperty().releaseDateProperty().bind(this.releaseDatePicker.valueProperty());

        this.authorComboBox.valueProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().authorFxProperty());
        this.categoryComboBox.valueProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().categoryFxProperty());
        this.titleTextField.textProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().titleProperty());
        this.descTextArea.textProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().ratingProperty());
        this.releaseDatePicker.valueProperty().bindBidirectional(this.gameModel.getGameFxObjectProperty().releaseDateProperty());

    }

    private void validation() {
        this.addGameButton.disableProperty().bind(this.titleTextField.textProperty().isEmpty()
                .or(this.categoryComboBox.valueProperty().isNull())
                .or(this.authorComboBox.valueProperty().isNull())
                .or(this.descTextArea.textProperty().isEmpty())
                .or(this.releaseDatePicker.valueProperty().isNull())
        );

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
            clearFields();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }

    @FXML
    public void clearControlsOnAction() {
        clearFields();
    }

    private void clearFields(){
        this.titleTextField.clear();
        this.categoryComboBox.getSelectionModel().clearSelection();
        this.authorComboBox.getSelectionModel().clearSelection();
        this.descTextArea.clear();
        this.ratingSlider.setValue(1);
        this.releaseDatePicker.getEditor().clear();
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
