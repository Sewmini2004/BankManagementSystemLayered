package lk.ijse.gdse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        URL resource = this.getClass().getResource("/lk/ijse/gdse/view/LoginForm.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    public void changeScene(String fxml) throws IOException {
        URL resource = this.getClass().getResource(fxml);
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        stg.setScene(scene);
        stg.setTitle("Account");
    }

    public static void main(String[] args) {
        launch(args);
    }
   }




