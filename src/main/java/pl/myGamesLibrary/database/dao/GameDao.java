package pl.myGamesLibrary.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.myGamesLibrary.database.models.Game;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class GameDao extends CommonDao {

    public GameDao( ) {
        super();
    }

    public void deleteByColumnName(String columnName, int id) throws ApplicationException, SQLException {
        Dao<Game, Object> dao = getDao(Game.class);
        DeleteBuilder<Game, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columnName, id);
        dao.delete(deleteBuilder.prepare());
    }
}
