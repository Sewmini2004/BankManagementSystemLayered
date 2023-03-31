package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.gdse.AppInitializer;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label Logins;

    @FXML
    private JFXButton btnLogin;

    public void initialize(){
        txtUserName.requestFocus();
    }

    @FXML
    void btnLoginOnAction1(ActionEvent event) throws IOException {
      checkLogin();
    }
    private void checkLogin() throws IOException {
        AppInitializer m = new AppInitializer();
        if (txtUserName.getText().toString().equals("sewmini") && txtPassword.getText().toString().equals("1234")
                || txtUserName.getText().toString().equals("pramodya") && txtPassword.getText().toString().equals("123456")) {
            Logins.setText("Success!");
            m.changeScene("/lk/ijse/gdse/view/DashBoardForm.fxml");
        } else if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
            Logins.setText("Please enter your User and Password !");
        } else {
            Logins.setText("Wrong username or password!");
        }
    }
}
