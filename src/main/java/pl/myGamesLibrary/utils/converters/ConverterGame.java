package pl.myGamesLibrary.utils.converters;


import pl.myGamesLibrary.database.models.Game;
import pl.myGamesLibrary.modelFx.GameFx;
import pl.myGamesLibrary.utils.Utils;

public class ConverterGame {

    public static Game convertToBook(GameFx gameFx){
        Game game = new Game();
        game.setId(gameFx.getId());
        game.setTitle(gameFx.getTitle());
        game.setDescription(gameFx.getDescription());
        game.setRating(gameFx.getRating());
        game.setReleaseDate(Utils.convertToDate(gameFx.getReleaseDate()));
        game.setAddedDate(Utils.convertToDate(gameFx.getAddedDate()));
        return game;
    }
}
