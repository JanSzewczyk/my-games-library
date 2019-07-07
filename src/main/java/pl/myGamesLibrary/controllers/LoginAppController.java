package pl.myGamesLibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.LoginModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class LoginAppController {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label wrongLabel;
    @FXML
    private Button loginButton;

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
            System.out.println("okno");
        }else {
            this.wrongLabel.setText(FxmlUtils.getResourceBundle().getString("login.wrong"));
        }
    }

    @FXML
    public void backButtonOnClick() {
        this.applicationController.loadScreen();
    }
    
    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }


   
}
