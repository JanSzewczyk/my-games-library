package pl.myGamesLibrary.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;

import java.io.IOException;
import java.util.Optional;


import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {

    public static final String MY_GAMES_LIBRARY_FXML = "/fxml/MyGamesLibrary.fxml";

    @FXML
    private BorderPane borderPane;
    @FXML
    private TopMenuButtonsController topMenuButtonsController;
    @FXML
    private UserPanelController userPanelController;

    private User user;

    @FXML
    private void initialize(){
        this.topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlPath) {
        if (fxmlPath.equals(MY_GAMES_LIBRARY_FXML)){
            FXMLLoader loader = FxmlUtils.getLoader(MY_GAMES_LIBRARY_FXML);
            Pane pane = new Pane();
            try {
                pane = loader.load();
            } catch (IOException e) {
                DialogUtils.errorDialog(e.getMessage());
            }

            MyGamesController myGamesController = loader.getController();
            myGamesController.setUser(this.user);

            borderPane.setCenter(pane);
        }else{
            borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
        }


    }

    @FXML
    public void closeApplication() {
        Optional<ButtonType> result = DialogUtils.confirmationDialog("exit.title", "exit.header", null);
        if(result.get() == ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    @FXML
    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    @FXML
    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    @FXML
    public void about() {
        DialogUtils.dialogAboutApplication();
    }


    public void setUserInApplication(User user) {
        this.user = user;
        this.userPanelController.setUser(user);
    }

    public User getUser() {
        return user;
    }
}
