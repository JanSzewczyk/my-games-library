package pl.myGamesLibrary.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import pl.myGamesLibrary.modelFx.MenuModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;

import java.io.IOException;

public class MenuAppController {

    public static final String LOGIN_APP_FXML = "/fxml/LoginApp.fxml";
    public static final String SIGN_UP_APP_FXML = "/fxml/SignUpApp.fxml";

    @FXML
    private ComboBox<String> languageComboBox;

    private ApplicationController applicationController;
    private MenuModel menuModel;

    @FXML
    public void initialize(){
        this.menuModel = new MenuModel();
        this.menuModel.init();

        this.languageComboBox.setItems(this.menuModel.getLanguages());
    }

    @FXML
    public void loginOnAction() {
        FXMLLoader loader = FxmlUtils.getLoader(LOGIN_APP_FXML);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginAppController loginAppController = loader.getController();
        loginAppController.setApplicationController(this.applicationController);
        applicationController.setScreen(pane);

    }

    @FXML
    public void signupOnAction() {
        FXMLLoader loader = FxmlUtils.getLoader(SIGN_UP_APP_FXML);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        SignUpAppController signUpAppController = loader.getController();
        signUpAppController.setApplicationController(this.applicationController);
        signUpAppController.setMenuAppController(this);
        applicationController.setScreen(pane);
    }

    @FXML
    public void aboutOnAction() {
        DialogUtils.dialogAboutApplication();
    }

    @FXML
    public void exitOnAction() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void languageComboBoxOnAction() {
        this.applicationController.setLanguage(this.languageComboBox.getSelectionModel().getSelectedItem());
        this.applicationController.loadScreen();
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }


}
