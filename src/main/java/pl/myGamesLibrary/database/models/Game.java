package pl.myGamesLibrary.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "GAMES")
public class Game implements BaseModel {

    public static final String AUTHOR_ID = "AUTHOR_ID";
    public static final String CATEGORY_ID = "CATEGORY_ID";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "TITLE", canBeNull = false, unique = true)
    private String title;

    @DatabaseField(columnName = AUTHOR_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Author author;

    @DatabaseField(columnName = CATEGORY_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Category category;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Product> products;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "RELEASE_DATE")
    private Date releaseDate;

    @DatabaseField(columnName = "RATING",   width = 2)
    private int rating;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;


    public Game(){

    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ForeignCollection<Product> getProducts() {
        return products;
    }

    public void setProducts(ForeignCollection<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}