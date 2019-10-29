package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene login, sellingScreen, inventoryScreen;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        window.setTitle("Genesis");
        sellingScreen = new Scene(root, 1920, 1080);
        //sellingScreen.getStylesheets().add("sample/Viper.css");
        window.setScene(sellingScreen);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
