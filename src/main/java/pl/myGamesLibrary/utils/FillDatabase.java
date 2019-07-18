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
        book2.setReleaseDate(new Date(115,4,14));
        book2.setAddedDate(new Date());
        book2.setDescription("Piąta, pełnoprawna odsłona niezwykle popularnej serii gier akcji, nad której rozwojem pieczę sprawuje studio Rockstar North we współpracy z koncernem Take Two Interactive. ");//

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
        category4.setName("RPG");

        Author author3 = new Author();
        author3.setName("CD Projekt Red");

        Game book3 = new Game();
        book3.setCategory(category4);
        book3.setAuthor(author3);
        book3.setTitle("Wiedźmin 3: Dziki Gon");
        book3.setRating(10);
        book3.setReleaseDate(new Date(115,5,19));
        book3.setAddedDate(new Date());
        book3.setDescription("Gra action RPG, stanowiąca trzecią część przygód Geralta z Rivii. Podobnie jak we wcześniejszych odsłonach cyklu, Wiedźmin 3: Dziki Gon bazuje na motywach twórczości literackiej Andrzeja Sapkowskiego.");//, jednak nie jest bezpośrednią adaptacją żadnej z jego książek.

        Game book4 = new Game();
        book4.setCategory(category4);
        book4.setAuthor(author3);
        book4.setTitle("Wiedźmin 2: Zabójcy królów");
        book4.setRating(10);
        book4.setReleaseDate(new Date());
        book4.setAddedDate(new Date());
        book4.setDescription("Piękna 2 część gry o wiedzminie Geraldzie.");

        GameDao gameDao = new GameDao();
        try {
            gameDao.creatOrUpdate(book1);
            gameDao.creatOrUpdate(book11);
            gameDao.creatOrUpdate(book2);
            gameDao.creatOrUpdate(book21);
            gameDao.creatOrUpdate(book22);
            gameDao.creatOrUpdate(book3);
            gameDao.creatOrUpdate(book4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        User user0 = new User();
        user0.setNick("Janeczek");
        user0.setEmail("jan.szewczyk1997@gmail.com");
        user0.setLangualge("PL");
        user0.setPassword("qazwsxedc");
        user0.setBirthDay(new Date(97,3,22));

        User user1 = new User();
        user1.setNick("Kinia123");
        user1.setEmail("kinia@gmail.com");
        user1.setLangualge("PL");
        user1.setPassword("qazwsxedc");
        user1.setBirthDay(new Date(92,2,14));

        User user2 = new User();
        user2.setNick("q");
        user2.setEmail("q");
        user2.setLangualge("ANG");
        user2.setPassword("q");
        user2.setBirthDay(new Date(92,12,14));

        UserDao userDao = new UserDao();
        try {
            userDao.creatOrUpdate(user0);
            userDao.creatOrUpdate(user1);
            userDao.creatOrUpdate(user2);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Product prod0 = new Product();
        prod0.setUser(user2);
        prod0.setGame(book3);
        prod0.setMyOpinion("Super gra, polecam!!! Super fabuła i postacie :)");
        prod0.setMyRating(10);

        Product prod1 = new Product();
        prod1.setUser(user2);
        prod1.setGame(book2);
        prod1.setMyOpinion("Piuu piuuu strzelanka)");
        prod1.setMyRating(9);

        ProductDao productDao = new ProductDao();
        try {
            productDao.creatOrUpdate(prod0);
            productDao.creatOrUpdate(prod1);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

}
