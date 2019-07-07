package pl.myGamesLibrary.Apps;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myGamesLibrary.database.dbuitls.DBManager;
import pl.myGamesLibrary.utils.FillDatabase;
import pl.myGamesLibrary.utils.FxmlUtils;

import java.util.Locale;

public class Application extends javafx.application.Application {

    public static final String APPLICATION_FXML = "/fxml/Application.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane borderPane = FxmlUtils.fxmlLoader(APPLICATION_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        System.out.println("elo");
        primaryStage.show();

        DBManager.initDatabase();
        FillDatabase.fillDatabase();
    }

}
