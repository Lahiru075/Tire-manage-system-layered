package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBo;
import lk.ijse.gdse.dao.costom.UserDao;
import lk.ijse.gdse.dto.Tm.UserTm;
import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.dao.costom.impl.UserDaoImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

//    UserDaoImpl userDao = new UserDaoImpl();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button butGenearateReport;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TableColumn<UserTm , String> colPassword;

    @FXML
    private TableColumn<UserTm , String> colRole;

    @FXML
    private TableColumn<UserTm , String> colUserId;

    @FXML
    private TableColumn<UserTm , String> colUsername;

    @FXML
    private Label labUserId;

    @FXML
    private TableView<UserTm> tbUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBo userBo = (UserBo) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void OnActionbutGenearateReport(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String userId = labUserId.getText();

        boolean isResult = userBo.delete(userId);

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "User Delete Successful").showAndWait();
            reset();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Delete UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String userId = labUserId.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String username = txtUsername.getText();

        if (userId.isEmpty() || role.isEmpty() || password.isEmpty() || username.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter usId , role , username and password").showAndWait();
            return;
        }

        String usernamePattern = "^[a-zA-Z0-9._-]{5,20}$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        boolean isValidUsername = username.matches(usernamePattern);
        boolean isValidPassword = password.matches(passwordPattern);

        if (!isValidUsername) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username").showAndWait();
            return;
        }

        if (!isValidPassword) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password").showAndWait();
            return;
        }

        boolean isResult = userBo.save(new UserDto(userId, role, password, username));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "User Save Successful").showAndWait();
            reset();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Save UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String userId = labUserId.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String username = txtUsername.getText();

        if (userId.isEmpty() || role.isEmpty() || password.isEmpty() || username.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter usId , role , username and password").showAndWait();
            return;
        }

        String usernamePattern = "^[a-zA-Z0-9._-]{5,20}$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        boolean isValidUsername = username.matches(usernamePattern);
        boolean isValidPassword = password.matches(passwordPattern);

        if (!isValidUsername) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username").showAndWait();
            return;
        }

        if (!isValidPassword) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password").showAndWait();
            return;
        }

        boolean isResult = userBo.update(new UserDto(userId, role, password, username));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "User Update Successful").showAndWait();
            reset();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Update UnSuccessful").showAndWait();
        }
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        UserTm userTm = tbUser.getSelectionModel().getSelectedItem();

        if (userTm != null) {
            labUserId.setText(userTm.getUsId());
            cmbRole.setValue(userTm.getRole());
            txtPassword.setText(userTm.getPassword());
            txtUsername.setText(userTm.getUsername());
        }

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        reset();
    }

    void getNextUserId() throws SQLException {
        String userId = userBo.getNextId();
        labUserId.setText(userId);
    }

    void loadUserRoles(){
        ObservableList<String> paymentOptions = FXCollections.observableArrayList("Admin", "Cashier");
        cmbRole.setItems(paymentOptions);
    }

    void loadAllUsers() throws SQLException {
        ArrayList<UserDto> allUsers = userBo.getAll();

        ObservableList<UserTm> observableList = FXCollections.observableArrayList();

        for (UserDto userDto : allUsers) {
            UserTm userTm = new UserTm(
                    userDto.getUsId(),
                    userDto.getRole(),
                    userDto.getPassword(),
                    userDto.getUsername()
            );
            observableList.add(userTm);
        }

        tbUser.setItems(observableList);
    }

    void reset() throws SQLException {
        getNextUserId();
        loadAllUsers();

        cmbRole.setValue("");
        txtPassword.setText("");
        txtUsername.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("usId"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        loadUserRoles();

        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
