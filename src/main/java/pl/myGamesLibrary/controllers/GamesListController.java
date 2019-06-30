package pl.myGamesLibrary.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.myGamesLibrary.modelFx.AuthorFx;
import pl.myGamesLibrary.modelFx.CategoryFx;
import pl.myGamesLibrary.modelFx.GameFx;
import pl.myGamesLibrary.modelFx.GamesListModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;

public class GamesListController {

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    public TableView<GameFx> gamesTableView;
    @FXML
    public TableColumn<GameFx, String> titleColumn;
    @FXML
    public TableColumn<GameFx, String> descColumn;
    @FXML
    public TableColumn<GameFx, AuthorFx> authorColumn;
    @FXML
    public TableColumn<GameFx, CategoryFx> categoryColumn;
    @FXML
    public TableColumn<GameFx, Number> ratingColumn;
    @FXML
    public TableColumn<GameFx, LocalDate> releaseColumn;
    @FXML
    public TableColumn<GameFx, GameFx> deleteColumn;
    @FXML
    public TableColumn<GameFx, GameFx> editColumn;

    private GamesListModel gamesListModel;

    public void initialize(){
        this.gamesListModel = new GamesListModel();
        try {
            this.gamesListModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.categoryComboBox.setItems(this.gamesListModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.gamesListModel.getAuthorFxObservableList());

        this.gamesListModel.categoryFxObjectPropertyProperty().bind(this.categoryComboBox.valueProperty());
        this.gamesListModel.authorFxObjectPropertyProperty().bind(this.authorComboBox.valueProperty());

        this.gamesTableView.setItems(this.gamesListModel.getGameFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());

        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));     //button
        this.deleteColumn.setCellFactory(param -> new TableCell<GameFx, GameFx>(){
            Button button = createButton("/icons/delete-button.png");
            @Override
            protected void updateItem(GameFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            gamesListModel.deleteGame(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });

        this.editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));     //button edit
        this.editColumn.setCellFactory(param -> new TableCell<GameFx, GameFx>(){
            Button button = createButton("/icons/edit.png");
            @Override
            protected void updateItem(GameFx item, boolean empty){
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddGame.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                        GameController controller = loader.getController();
                        controller.getGameModel().setGameFxObjectProperty(item);
                        controller.bindings();
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    });
                }
            }
        });

    }

    private Button createButton(String path){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource(path).toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        button.setGraphic(imageView);
        return button;
    }

    public void filterOnActionComboBox() {
        this.gamesListModel.filterGamesList();
    }

    public void clearCategoryComboBox() {
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    public void clearAuthorComboBox() {
        this.authorComboBox.getSelectionModel().clearSelection();
    }
}
