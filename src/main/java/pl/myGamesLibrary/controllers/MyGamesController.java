package pl.myGamesLibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.MyGamesModel;
import pl.myGamesLibrary.modelFx.ProductFx;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;

public class MyGamesController {

    @FXML
    private Button deleteButton;
    @FXML
    private ListView<ProductFx> gamesListView;
    @FXML
    private Label gameTitleLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label releaseDateLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label myOpinionLabel;
    @FXML
    private Label myRatingLabel;

    private User user;
    private MyGamesModel myGamesModel;

    @FXML
    public void initialize(){
        this.myGamesModel = new MyGamesModel();

        this.deleteButton.disableProperty().bind(this.myGamesModel.productFxObjectPropertyProperty().isNull());
    }

    private void initBindings(){
        this.gameTitleLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().titleProperty());
        this.descLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().descriptionProperty());
        this.authorLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().getAuthorFx().nameProperty());
        this.categoryLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().getCategoryFx().getNameProperty());
        this.releaseDateLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().releaseDateProperty().asString());
        this.ratingLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().getGameFx().ratingProperty().asString());
        this.myOpinionLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().myOpinionProperty());
        this.myRatingLabel.textProperty().bind(this.myGamesModel.getProductFxObjectProperty().myRatingProperty().asString());
    }

    @FXML
    public void selectGameOnMouseClicked() {
        if(this.gamesListView.getSelectionModel().getSelectedItem() != null){
            this.myGamesModel.setProductFxObjectProperty(this.gamesListView.getSelectionModel().getSelectedItem());
            initBindings();
        }
    }

    @FXML
    public void deleteOnAction() {
        try {
            this.myGamesModel.deleteProduct();
        } catch (ApplicationException | SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        this.myGamesModel.initEmptyProduct();
    }

    @FXML
    public void addProductOnAction() {
        this.myGamesModel.setProductFxObjectProperty(null);
        try {
            this.myGamesModel.addProduct();
        } catch (SQLException | ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void setUser(User user) {
        this.user = user;
        this.myGamesModel.setUser(user);

        try {
            this.myGamesModel.init();
        } catch (SQLException | ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.gamesListView.setItems(this.myGamesModel.getProductFxObservableList());
    }


}
