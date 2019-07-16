package pl.myGamesLibrary.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "USERS")
public class User implements BaseModel {

    public static final String NICK = "NICK";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    @DatabaseField(generatedId = true)
    private int id;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Product> products;

    @DatabaseField(columnName = NICK, canBeNull = false, unique = true)
    private String nick;

    @DatabaseField(columnName = EMAIL, canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;

    @DatabaseField(columnName = "LANGUAGE", canBeNull = false)
    private String langualge;

    @DatabaseField(columnName = "BIRTH_DAY")
    private Date birthDay;


    public User() {
    }


    public int getId() {
        return id;
    }

    public ForeignCollection<Product> getProducts() {
        return products;
    }

    public void setProducts(ForeignCollection<Product> products) {
        this.products = products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLangualge() {
        return langualge;
    }

    public void setLangualge(String langualge) {
        this.langualge = langualge;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", products=" + products +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", langualge='" + langualge + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
