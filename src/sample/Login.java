package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable{
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton resetButton;

    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField password;

    @FXML
    private Label invalidCredentials;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setDefaultButton(true);
    }

    @FXML
    public void onResetButtonClicked() {
        userNameField.setText("");
        password.setText("");
    }

    @FXML
    public void onLoginButtonClicked() throws Exception {
        String user = userNameField.getText();
        String pass = password.getText();
        Employee emp = GenesisDAO.searchEmployee(user, pass);
        String genPass = emp.getGenesisPassword();
        String genUser = emp.getGenesisUsername();
        if (genUser.equals(user) && genPass.equals(pass)) {
            // TODO: 3/15/2020 need to make this open the selling screen if successful
            System.out.println("successful Login");
            String sceneChange = "SellingScreen.fxml";
            SceneController.changeScene(sceneChange);
        } else {
            System.out.println("Invalid username or password");
            invalidCredentials.setText("Invalid username or password please try again");
        }
    }

}
