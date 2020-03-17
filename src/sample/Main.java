package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); // this is for the login screen
        //Parent root = FXMLLoader.load(getClass().getResource("SellingScreen.fxml")); //this is for the main selling screen

        window.setTitle("Genesis");
        scene = new Scene(root, 1920, 1080);
        //sellingScreen.getStylesheets().add("sample/Viper.css");
        window.setScene(scene);
        window.show();

         */
        SceneController scene = new SceneController();
        scene.scene(primaryStage);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
