package pl.myGamesLibrary.utils.converters;

import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.modelFx.UserFx;
import pl.myGamesLibrary.utils.Utils;

public class ConverterUser {

    public static User convertToUser(UserFx userFx){
        User user = new User();
        user.setNick(userFx.getNick());
        user.setEmail(userFx.getEmail());
        user.setPassword(userFx.getPassword());
        user.setLangualge(userFx.getLanguage());
        user.setBirthDay(Utils.convertToDate(userFx.getBirthDay()));
        return user;
    }

    public  static UserFx convertToUserFx(User user){
        UserFx userFx = new UserFx();
        userFx.setNick(user.getNick());
        userFx.setEmail(user.getEmail());
        userFx.setPassword(user.getPassword());
        userFx.setLanguage(user.getLangualge());
        userFx.setBirthDay(Utils.convertToLocalDate(user.getBirthDay()));
        return userFx;
    }
}
