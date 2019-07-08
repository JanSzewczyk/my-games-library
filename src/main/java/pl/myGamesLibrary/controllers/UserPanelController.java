package pl.myGamesLibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.UserPanelModel;
import pl.myGamesLibrary.utils.converters.ConverterUser;

public class UserPanelController {

    @FXML
    private Label nickLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthLabel;

    private UserPanelModel userPanelModel;

    @FXML
    public void initialize(){
        this.userPanelModel = new UserPanelModel();

    }

    public void setUser(User user){
        this.nickLabel.setText(user.getNick());
        this.emailLabel.setText(user.getEmail());
        this.birthLabel.setText(user.getBirthDay().getDay() + "/" + user.getBirthDay().getMonth() + "/" + (1900 + user.getBirthDay().getYear()));
    }
}
