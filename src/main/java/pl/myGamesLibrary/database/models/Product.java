package pl.myGamesLibrary.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRODUCTS")
public class Product implements BaseModel {

    public static final String GAME_ID = "GAME_ID";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "USER_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private User user;

    @DatabaseField(columnName = GAME_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Game game;

    @DatabaseField(columnName = "MY_RATING",   width = 2)
    private int myRating;

    @DatabaseField(columnName = "MY_OPINION")
    private String myOpinion;


    public Product() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getMyRating() {
        return myRating;
    }

    public void setMyRating(int myRating) {
        this.myRating = myRating;
    }

    public String getMyOpinion() {
        return myOpinion;
    }

    public void setMyOpinion(String myOpinion) {
        this.myOpinion = myOpinion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", user=" + user +
                ", game=" + game +
                ", myRating=" + myRating +
                ", myOpinion='" + myOpinion + '\'' +
                '}';
    }
}
