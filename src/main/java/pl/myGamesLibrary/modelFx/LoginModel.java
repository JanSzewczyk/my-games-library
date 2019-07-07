package pl.myGamesLibrary.modelFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.myGamesLibrary.database.dao.UserDao;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class LoginModel {

    private StringProperty emailProperty = new SimpleStringProperty();
    private StringProperty passwordProperty = new SimpleStringProperty();

    public User loginUser() throws SQLException, ApplicationException {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findUser(User.EMAIL, this.getEmailProperty(), User.PASSWORD, this.getPasswordProperty());
        if(users.size() == 1)
            return users.get(0);
        else
            return null;
    }

    public String getEmailProperty() {
        return emailProperty.get();
    }

    public StringProperty emailPropertyProperty() {
        return emailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty.set(emailProperty);
    }

    public String getPasswordProperty() {
        return passwordProperty.get();
    }

    public StringProperty passwordPropertyProperty() {
        return passwordProperty;
    }

    public void setPasswordProperty(String passwordProperty) {
        this.passwordProperty.set(passwordProperty);
    }
}
