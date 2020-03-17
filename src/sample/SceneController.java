package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static int instanceCounter = 0;
    private static Stage window;
    private static Scene scene;

    public void scene(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); // this is for the login screen
        //Parent root = FXMLLoader.load(getClass().getResource("SellingScreen.fxml")); //this is for the main selling screen

        window.setTitle("Genesis");
        scene = new Scene(root, 1920, 1080);
        //sellingScreen.getStylesheets().add("sample/Viper.css");
        window.setScene(scene);
        window.show();
        instanceCounter++;
    }
    public static void changeScene(String FXML) throws Exception {
        Parent pane = FXMLLoader.load(SceneController.class.getResource(FXML));
        window.getScene().setRoot(pane);
    }
    public static Scene getScene(){
        return scene;
    }
    public Stage GetStage(){
        return window;
    }
    public int getInstanceCounter(){
        return instanceCounter;
    }




}
