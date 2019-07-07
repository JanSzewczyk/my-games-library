package pl.myGamesLibrary.utils;

import pl.myGamesLibrary.database.dao.CategoryDao;
import pl.myGamesLibrary.database.dao.GameDao;
import pl.myGamesLibrary.database.dao.UserDao;
import pl.myGamesLibrary.database.dbuitls.DBManager;
import pl.myGamesLibrary.database.models.Author;
import pl.myGamesLibrary.database.models.Category;
import pl.myGamesLibrary.database.models.Game;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.exceptions.ApplicationException;

import java.util.Date;

public class FillDatabase {

    public static  void fillDatabase(){

        Category category1 = new Category();
        category1.setName("MMORPG");

        Author author1 = new Author();
        author1.setName("Blizzard Entertainment");
        Game book1 = new Game();
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setTitle("World of Warcraft");
        book1.setRating(6);
        book1.setReleaseDate(new Date());
        book1.setAddedDate(new Date());
        book1.setDescription("Zajefajna gra internetowa.");

        Author author11 = new Author();
        author11.setName("NCsoft");
        Game book11 = new Game();
        book11.setCategory(category1);
        book11.setAuthor(author11);
        book11.setTitle("Lineage II");
        book11.setRating(4);
        book11.setReleaseDate(new Date());
        book11.setAddedDate(new Date());
        book11.setDescription("Przestażała gra, ale fanu dużo.");

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Category category2 = new Category();
        category2.setName("Strategia");

////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        Game book2 = new Game();
        book2.setCategory(category3);
        book2.setAuthor(author2);
        book2.setTitle("Grand Theft Auto V");
        book2.setRating(9);
        book2.setReleaseDate(new Date());
        book2.setAddedDate(new Date());
        book2.setDescription("Super fabuła, gangsterskie życie to coś.");

        Game book21 = new Game();
        book21.setCategory(category3);
        book21.setAuthor(author2);
        book21.setTitle("Red Dead Redemption 2");
        book21.setRating(10);
        book21.setReleaseDate(new Date());
        book21.setAddedDate(new Date());
        book21.setDescription("Super ciekawa gra w super klimacie.");

        Author author21 = new Author();
        author21.setName("Infinity Ward / Beenox Inc.");
        Game book22 = new Game();
        book22.setCategory(category3);
        book22.setAuthor(author21);
        book22.setTitle("Call of Duty: Modern Warfare");
        book22.setRating(10);
        book22.setReleaseDate(new Date());
        book22.setAddedDate(new Date());
        book22.setDescription("Super strzelanka z jeszcze lepszym voiceactingiem.");

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Category category4 = new Category();
        category4.setName("Fabularna");

        Author author3 = new Author();
        author3.setName("CD Projekt Red");

        Game book3 = new Game();
        book3.setCategory(category4);
        book3.setAuthor(author3);
        book3.setTitle("Wiedźmin 3: Dziki Gon");
        book3.setRating(10);
        book3.setReleaseDate(new Date());
        book3.setAddedDate(new Date());
        book3.setDescription("Najlepsza gra wszechczasów.");

        Game book4 = new Game();
        book4.setCategory(category4);
        book4.setAuthor(author3);
        book4.setTitle("Wiedźmin 2: Zabójcy królów");
        book4.setRating(10);
        book4.setReleaseDate(new Date());
        book4.setAddedDate(new Date());
        book4.setDescription("Piękna 2 część gry o wiedzminie Geraldzie.");

        GameDao bookDao = new GameDao();
        try {
            bookDao.creatOrUpdate(book1);
            bookDao.creatOrUpdate(book11);
            bookDao.creatOrUpdate(book2);
            bookDao.creatOrUpdate(book21);
            bookDao.creatOrUpdate(book22);
            bookDao.creatOrUpdate(book3);
            bookDao.creatOrUpdate(book4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }





        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        User user0 = new User();
        user0.setNick("Janeczek");
        user0.setEmail("jan@gmail.com");
        user0.setLangualge("PL");
        user0.setPassword("qazwsxedc");
        user0.setBirthDay(new Date(97,3,22));

        User user1 = new User();
        user1.setNick("Kinia123");
        user1.setEmail("kinia@gmail.com");
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
    }

}
