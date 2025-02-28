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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBo;
import lk.ijse.gdse.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public boolean check = false;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button butLogin;

    @FXML
    private Button butSingIn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBo userBo = (UserBo) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void butLoginOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String username = txtUsername.getText();


        if (password.isEmpty() || username.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter username and password").show();
        } else {
            try {
                UserDto userDto = userBo.checkUser(username, password);
                if (userDto != null) {
                    Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
                    currentStage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
                    Parent root = loader.load();

                    DashBoardController controller = loader.getController();
                    controller.initialize(userDto); // UserDto object eka set karanawa
                    Stage stage = new Stage();
                    Image image = new Image(getClass().getResourceAsStream("/images/tire.png"));
                    stage.getIcons().add(image);
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please log in to continue.").showAndWait();
                    txtUsername.clear();
                    txtPassword.clear();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "UI loading error").show();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "UI error").show();
            }
        }
    }

    @FXML
    void butSingInOnAction(ActionEvent event) throws IOException {
        mainAnchorPane.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signUpForm.fxml"));
        mainAnchorPane.getChildren().add(anchorPane);
    }

}
