package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {


    @FXML private Button loginButton;
    @FXML private Button resetButton;

    @FXML private TextField userName;
    @FXML private TextField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setDefaultButton(true);



    }

    @FXML public void onResetButtonClicked(){
        userName.setText("");
        password.setText("");
    }

    @FXML public void onLoginButtonClicked() throws SQLException {
        Connection conn = null;

    }



}
