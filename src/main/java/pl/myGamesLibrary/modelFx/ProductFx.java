package pl.myGamesLibrary.modelFx;

import javafx.beans.property.*;


public class ProductFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<UserFx> userFx= new SimpleObjectProperty<>();
    private ObjectProperty<GameFx> gameFx= new SimpleObjectProperty<>();
    private IntegerProperty myRating = new SimpleIntegerProperty();
    private StringProperty myOpinion = new SimpleStringProperty();


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public UserFx getUserFx() {
        return userFx.get();
    }

    public ObjectProperty<UserFx> userFxProperty() {
        return userFx;
    }

    public void setUserFx(UserFx userFx) {
        this.userFx.set(userFx);
    }

    public GameFx getGameFx() {
        return gameFx.get();
    }

    public ObjectProperty<GameFx> gameFxProperty() {
        return gameFx;
    }

    public void setGameFx(GameFx gameFx) {
        this.gameFx.set(gameFx);
    }

    public int getMyRating() {
        return myRating.get();
    }

    public IntegerProperty myRatingProperty() {
        return myRating;
    }

    public void setMyRating(int myRating) {
        this.myRating.set(myRating);
    }

    public String getMyOpinion() {
        return myOpinion.get();
    }

    public StringProperty myOpinionProperty() {
        return myOpinion;
    }

    public void setMyOpinion(String myOpinion) {
        this.myOpinion.set(myOpinion);
    }

    @Override
    public String toString() {
        return this.gameFx.get().titleProperty().getValue();
    }
}
