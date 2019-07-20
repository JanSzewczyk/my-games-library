package pl.myGamesLibrary.modelFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuModel {

    private ObservableList<String> languages = FXCollections.observableArrayList();


    public void init(){
        this.languages.add("ANG");
        this.languages.add("DE");
        this.languages.add("PL");
    }


    public ObservableList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ObservableList<String> languages) {
        this.languages = languages;
    }
}
