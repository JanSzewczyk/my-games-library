package pl.myGamesLibrary.utils;

import pl.myGamesLibrary.database.dao.CategoryDao;
import pl.myGamesLibrary.database.dao.GameDao;
import pl.myGamesLibrary.database.dao.ProductDao;
import pl.myGamesLibrary.database.dao.UserDao;
import pl.myGamesLibrary.database.models.*;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.util.Date;

public class FillDatabase {

    public static  void fillDatabase(){

        Category category1 = new Category();
        category1.setName("MMORPG");

        Author author1 = new Author();
        author1.setName("Blizzard Entertainment");

        Game game1 = new Game();
        game1.setCategory(category1);
        game1.setAuthor(author1);
        game1.setTitle("World of Warcraft");
        game1.setRating(6);
        game1.setReleaseDate(new Date(104,10,23));
        game1.setAddedDate(new Date());
        game1.setDescription("World of WarCraft is a position that belongs to the cRPG genre. It has been prepared especially for the multiplayer online multiplayer game, where thousands of players from around the world can stand in the lists.");

        Author author11 = new Author();
        author11.setName("NCsoft");
        Game game11 = new Game();
        game11.setCategory(category1);
        game11.setAuthor(author11);
        game11.setTitle("Lineage II");
        game11.setRating(4);
        game11.setReleaseDate(new Date(104,3,27));
        game11.setAddedDate(new Date());
        game11.setDescription("One of the more well-known and respected MMOs in the history of this genre. Production takes place in a fantasy world inhabited by people, elves, dwarves and other beings.");

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Category category2 = new Category();
        category2.setName("Strategy");

        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.creatOrUpdate(category2);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Category category3 = new Category();
        category3.setName("FPS");

        Author author2 = new Author();
        author2.setName("Rockstar Games");

        Game game2 = new Game();
        game2.setCategory(category3);
        game2.setAuthor(author2);
        game2.setTitle("Grand Theft Auto V");
        game2.setRating(9);
        game2.setReleaseDate(new Date(115,3,14));
        game2.setAddedDate(new Date());
        game2.setDescription("Another edition of the cult series of gangster action games Rockstar North - takes us to the world modeled on California. In the Grand Theft Auto universe, the state is called San Andreas and consists of, among others, the city of Los Santos");

        Game game21 = new Game();
        game21.setCategory(category3);
        game21.setAuthor(author2);
        game21.setTitle("Red Dead Redemption 2");
        game21.setRating(10);
        game21.setReleaseDate(new Date(118,9,26));
        game21.setAddedDate(new Date());
        game21.setDescription("The third part of the series Red Dead, and at the same time the prequel of the best-selling Red Dead Redemption. In Red Dead Redemption II, players reach the Wild West in 1899 to face Arthur Morgan's problems with the Dutch van der Linde gang.");

        Author author21 = new Author();
        author21.setName("Infinity Ward / Beenox Inc.");

        Game game22 = new Game();
        game22.setCategory(category3);
        game22.setAuthor(author21);
        game22.setTitle("Call of Duty: Modern Warfare");
        game22.setRating(10);
        game22.setReleaseDate(new Date(119,9,25));
        game22.setAddedDate(new Date());
        game22.setDescription("The sixteenth main part of the shooting cycle Call of Duty, and also the reboot of the Modern Warfare sub-series. Players play here in elite soldiers and take part in military operations around the globe.");

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Category category4 = new Category();
        category4.setName("RPG");

        Author author3 = new Author();
        author3.setName("CD Projekt Red");

        Game game3 = new Game();
        game3.setCategory(category4);
        game3.setAuthor(author3);
        game3.setTitle("The Witcher 3: Wild Hunt");
        game3.setRating(10);
        game3.setReleaseDate(new Date(115,4,19));
        game3.setAddedDate(new Date());
        game3.setDescription("Action RPG, which is the third part of the adventures of Geralt of Rivia. As in the earlier installments of the series, The Witcher 3: Wild Hunt is based on the literary motifs of Andrzej Sapkowski.");

        Game game4 = new Game();
        game4.setCategory(category4);
        game4.setAuthor(author3);
        game4.setTitle("The Witcher 2: Assassins of Kings");
        game4.setRating(10);
        game4.setReleaseDate(new Date(111,4,17));
        game4.setAddedDate(new Date());
        game4.setDescription("The second version of The Witcher takes place immediately after the events known from the first and continues the story of Geralt's attempt to defeat King Foltest.");

        GameDao gameDao = new GameDao();
        try {
            gameDao.creatOrUpdate(game1);
            gameDao.creatOrUpdate(game11);
            gameDao.creatOrUpdate(game2);
            gameDao.creatOrUpdate(game21);
            gameDao.creatOrUpdate(game22);
            gameDao.creatOrUpdate(game3);
            gameDao.creatOrUpdate(game4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        User user0 = new User();
        user0.setNick("TypicalUser");
        user0.setEmail("typical.user@xyz.com");
        user0.setLangualge("ANG");
        user0.setPassword("typical123");
        user0.setBirthDay(new Date(97,3,22));

        User user1 = new User();
        user1.setNick("UsErMonster");
        user1.setEmail("monser@xyz.com");
        user1.setLangualge("PL");
        user1.setPassword("qazwsxedc");
        user1.setBirthDay(new Date(92,2,14));

        UserDao userDao = new UserDao();
        try {
            userDao.creatOrUpdate(user0);
            userDao.creatOrUpdate(user1);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Product prod0 = new Product();
        prod0.setUser(user0);
        prod0.setGame(game3);
        prod0.setMyOpinion("Masterpiece! The best game I've ever played! The previous two parts from the Witcher series were very good, but not as much as the third. To all who played or did not play the previous part wholeheartedly recommend this game.");
        prod0.setMyRating(10);

        Product prod1 = new Product();
        prod1.setUser(user0);
        prod1.setGame(game2);
        prod1.setMyOpinion("The game is noteworthy. The Single Player is extensive enough to enjoy the variety of activities that a player can do. Multiplayer - the heart of the game surrounded by cheaters - a pity.");
        prod1.setMyRating(8);

        ProductDao productDao = new ProductDao();
        try {
            productDao.creatOrUpdate(prod0);
            productDao.creatOrUpdate(prod1);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

}
