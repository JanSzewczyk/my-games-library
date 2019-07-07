package pl.myGamesLibrary.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends CommonDao {

    public UserDao() {
        super();
    }

    public List<User> findByColumnName(String columnName, String field) throws ApplicationException, SQLException {
        Dao<User, Object> dao = getDao(User.class);
        QueryBuilder<User, Object> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(columnName, field);
        PreparedQuery<User> prepare = queryBuilder.prepare();
        return dao.query(prepare);
    }

    public List<User> findUser(String columnName0, String field0, String columnName1, String field1) throws ApplicationException, SQLException {
        Dao<User, Object> dao = getDao(User.class);
        QueryBuilder<User, Object> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(columnName0, field0).and().eq(columnName1, field1);
        PreparedQuery<User> prepare = queryBuilder.prepare();
        return dao.query(prepare);
    }


}
