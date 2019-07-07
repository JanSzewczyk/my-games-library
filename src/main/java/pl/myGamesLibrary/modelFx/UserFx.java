package pl.myGamesLibrary.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class UserFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nick = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty language = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthDay = new SimpleObjectProperty<>();


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNick() {
        return nick.get();
    }

    public SimpleStringProperty nickProperty() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick.set(nick);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public LocalDate getBirthDay() {
        return birthDay.get();
    }

    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay.set(birthDay);
    }
}
