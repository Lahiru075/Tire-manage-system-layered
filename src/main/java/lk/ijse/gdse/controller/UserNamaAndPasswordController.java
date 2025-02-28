package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UserNamaAndPasswordController {

    @FXML
    private Button butOk;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void butOkOnAction(ActionEvent event) throws SQLException, IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter username and password").showAndWait();
            return;
        }

        if (username.equals("MainAdmin") && password.equals("password@1234")) {
            Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
            currentStage.close();

            Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.setTitle("DashBoard");
            stage.setResizable(false);
            stage.show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Username or password is incorrect").showAndWait();
            txtUsername.clear();
            txtPassword.clear();
        }
//            UserDto userDto = loginModel.checkUser(username, password);
//
//            if (userDto.getRole() != null) {
//
//                if (userDto.getRole().toLowerCase().equals("admin")) {
////                    Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
////                    currentStage.close();
//
//                    Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
//                    currentStage.close();
//
//                    Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
//                    Stage stage = new Stage();
//                    Scene scene = new Scene(load);
//                    stage.setScene(scene);
//                    stage.setTitle("DashBoard");
//                    stage.setResizable(false);
//                    stage.show();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Username or password is incorrect").showAndWait();
//                    txtUsername.clear();
//                    txtPassword.clear();
//                }
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Username or password is incorrect").showAndWait();
//                txtUsername.clear();
//                txtPassword.clear();
//            }

    }

}
