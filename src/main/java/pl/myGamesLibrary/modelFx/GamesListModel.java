package pl.myGamesLibrary.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.myGamesLibrary.database.dao.AuthorDao;
import pl.myGamesLibrary.database.dao.CategoryDao;
import pl.myGamesLibrary.database.dao.GameDao;
import pl.myGamesLibrary.database.models.Author;
import pl.myGamesLibrary.database.models.Category;
import pl.myGamesLibrary.database.models.Game;
import pl.myGamesLibrary.utils.converters.ConverterAuthor;
import pl.myGamesLibrary.utils.converters.ConverterCategory;
import pl.myGamesLibrary.utils.converters.ConverterGame;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GamesListModel {

    private ObservableList<GameFx> gameFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList= FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private List<GameFx> gameFxList = new ArrayList<>();


    public void init() throws ApplicationException {
        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.queryForAll(Game.class);
        gameFxList.clear();
        games.forEach(game -> {
            this.gameFxList.add(ConverterGame.convertToGameFx(game));
        });

        this.gameFxObservableList.setAll(gameFxList);

        initAuthors();
        initCategory();
    }

    private void initAuthors() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);

        this.authorFxObservableList.clear();
        authorList.forEach(c -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(c);
            this.authorFxObservableList.add(authorFx);
        });
    }

    private void initCategory() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);

        this.categoryFxObservableList.clear();
        categoryList.forEach(c -> {
            CategoryFx categoryFx = ConverterCategory.covertToCategoryFx(c);
            this.categoryFxObservableList.add(categoryFx);
        });
    }

    public void filterGamesList(){
        if(getAuthorFxObjectProperty() != null && getCategoryFxObjectProperty() != null){
            filterPredicate(predicateAuthor().and(predicateCategory()));
        } else if (getCategoryFxObjectProperty() != null){
            filterPredicate(predicateCategory());
        }else if (getAuthorFxObjectProperty() != null){
            filterPredicate(predicateAuthor());
        }else {
            this.gameFxObservableList.setAll(this.gameFxList);
        }
    }

    private Predicate<GameFx> predicateCategory(){
        return gameFx -> gameFx.getCategoryFx().getId() == getCategoryFxObjectProperty().getId();
    }

    private Predicate<GameFx> predicateAuthor(){
        return gameFx -> gameFx.getAuthorFx().getId() == getAuthorFxObjectProperty().getId();
    }

    private void filterPredicate(Predicate<GameFx> predicate){
        List<GameFx> newList = gameFxList.stream().filter(predicate).collect(Collectors.toList());
        this.gameFxObservableList.setAll(newList);
    }

    public void deleteGame(GameFx gameFx) throws ApplicationException {
        GameDao gameDao = new GameDao();
        gameDao.deleteById(Game.class, gameFx.getId());
        init();
    }


    public ObservableList<GameFx> getGameFxObservableList() {
        return gameFxObservableList;
    }

    public void setGameFxObservableList(ObservableList<GameFx> gameFxObservableList) {
        this.gameFxObservableList = gameFxObservableList;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }


}
