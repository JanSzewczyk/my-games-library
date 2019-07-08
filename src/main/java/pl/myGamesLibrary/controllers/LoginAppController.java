package pl.myGamesLibrary.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.LoginModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class LoginAppController {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label wrongLabel;
    @FXML
    private Button loginButton;

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    private ApplicationController applicationController;
    private LoginModel loginModel;

    @FXML
    public void initialize(){
        this.loginModel = new LoginModel();

        this.loginModel.emailPropertyProperty().bind(this.emailTextField.textProperty());
        this.loginModel.passwordPropertyProperty().bind(this.passwordTextField.textProperty());
        this.loginButton.disableProperty().bind(this.loginModel.emailPropertyProperty().isEmpty().or(this.loginModel.passwordPropertyProperty().isEmpty()));
    }

    @FXML
    public void loginOnAction() {
        User user = new User();

        try {
            user = this.loginModel.loginUser();
        } catch (ApplicationException | SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        if(user != null){
            this.wrongLabel.setText("");
            Locale.setDefault(new Locale(user.getLangualge().toLowerCase()));
            openApplication(user);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }else {
            this.wrongLabel.setText(FxmlUtils.getResourceBundle().getString("login.wrong"));
        }
    }

    private void openApplication(User user) {
        FXMLLoader loader = FxmlUtils.getLoader(BORDER_PANE_MAIN_FXML);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        stage.initModality(Modality.WINDOW_MODAL);
        MainController mainController = loader.getController();
        mainController.setUserInApplication(user);
        stage.show();
    }

    @FXML
    public void backButtonOnClick() {
        this.applicationController.loadScreen();
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
}
