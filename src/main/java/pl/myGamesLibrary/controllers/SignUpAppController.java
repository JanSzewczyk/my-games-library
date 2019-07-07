package pl.myGamesLibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.SignUpModel;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;
import java.util.Optional;

public class SignUpAppController {


    @FXML
    private TextField nickTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    public PasswordField passTextField;
    @FXML
    public PasswordField repPassTextField;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private Button signUpButton;

    private ApplicationController applicationController;
    private MenuAppController menuAppController;
    private SignUpModel signUpModel;

    @FXML
    public void initialize(){
        this.signUpModel = new SignUpModel();
        this.signUpModel.init();

        bindings();
    }

    private void bindings() {
        this.signUpModel.getUserFxObjectProperty().nickProperty().bind(this.nickTextField.textProperty());
        this.signUpModel.getUserFxObjectProperty().emailProperty().bind(this.emailTextField.textProperty());
        this.signUpModel.getUserFxObjectProperty().passwordProperty().bind(this.passTextField.textProperty());
        this.signUpModel.getUserFxObjectProperty().languageProperty().bind(this.languageComboBox.valueProperty());
        this.signUpModel.getUserFxObjectProperty().birthDayProperty().bind(this.birthDatePicker.valueProperty());

        this.languageComboBox.setItems(this.signUpModel.getLanguages());

        this.signUpButton.disableProperty().bind(this.signUpModel.getUserFxObjectProperty().nickProperty().isEmpty()
                .or(this.signUpModel.getUserFxObjectProperty().emailProperty().isEmpty())
                .or(this.signUpModel.getUserFxObjectProperty().passwordProperty().isEmpty())
                .or(this.signUpModel.getUserFxObjectProperty().languageProperty().isNull())
                .or(this.signUpModel.getUserFxObjectProperty().birthDayProperty().isNull()));

    }

    @FXML
    public void SignUpOnAction() {
        if (this.verifyUserInDaraBase() && this.validation()){
            try {
                this.signUpModel.saveUserInDataBase();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }

            Optional<ButtonType> result =DialogUtils.confirmationDialog("signup.util.title", "signup.util.header", "signup.util.content");
            if(result.get() == ButtonType.OK){
                this.menuAppController.loginOnAction();
            }
        }

    }

    private Boolean verifyUserInDaraBase(){
        if(this.signUpModel.checkUser(User.NICK, this.signUpModel.getUserFxObjectProperty().getNick())){
            this.nickTextField.setText(null);
            this.nickTextField.setPromptText(FxmlUtils.getResourceBundle().getString("signup.valid.using.nick"));
            return false;
        }

        if (this.signUpModel.checkUser(User.EMAIL, this.signUpModel.getUserFxObjectProperty().getEmail())){
            this.emailTextField.setText(null);
            this.emailTextField.setPromptText(FxmlUtils.getResourceBundle().getString("signup.valid.using.mail"));
            return false;
        }

        return true;
    }

    private Boolean validation() {
        if(this.signUpModel.getUserFxObjectProperty().getNick().length() < 6){
            this.nickTextField.setText(null);
            this.nickTextField.setPromptText(FxmlUtils.getResourceBundle().getString("signup.valid.min.nick"));
            return false;
        }

        if(this.signUpModel.getUserFxObjectProperty().getPassword().length() < 6) {
            this.passTextField.setText(null);
            this.passTextField.setPromptText(FxmlUtils.getResourceBundle().getString("signup.valid.min.pass"));
            return false;
        }

        if(!this.signUpModel.getUserFxObjectProperty().getPassword().equals(this.repPassTextField.getText())) {
            this.repPassTextField.setText(null);
            this.repPassTextField.setPromptText(FxmlUtils.getResourceBundle().getString("signup.valid.reppas"));
            return false;
        }

        return true;
    }

    @FXML
    public void backOnAction() {
        this.applicationController.loadScreen();
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void setMenuAppController(MenuAppController menuAppController) {
        this.menuAppController = menuAppController;
    }
}
