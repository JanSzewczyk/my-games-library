package pl.myGamesLibrary.Apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myGamesLibrary.database.dbuitls.DBManager;
import pl.myGamesLibrary.database.models.User;
import pl.myGamesLibrary.utils.FillDatabase;
import pl.myGamesLibrary.utils.FxmlUtils;

import java.util.Locale;

public class MyGamesLibrary extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setLanguage("PL");
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DBManager.initDatabase();
        FillDatabase.fillDatabase();
    }

    public void setLanguage(String language){
        Locale.setDefault(new Locale(language));
    }
}
