package pl.myGamesLibrary.Apps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myGamesLibrary.utils.DialogUtils;
import pl.myGamesLibrary.utils.FxmlUtils;

import static pl.myGamesLibrary.utils.FxmlUtils.getResourceBundle;

public class Login extends Application {

    public static final String LOGIN_FXML = "/fxml/Login.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        XMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(LOGIN_FXML));
        loader.setResources(getResourceBundle());
        try {
             loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        Pane borderPane = FxmlUtils.fxmlLoader(LOGIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getResourimport javafx.fxml.FXMLLoader;ceBundle().getString("title.application"));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
