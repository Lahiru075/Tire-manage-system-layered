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
import lk.ijse.gdse.bo.custom.EmployeeBo;
import lk.ijse.gdse.dto.EmployeeDto;
import lk.ijse.gdse.dto.Tm.EmployeeTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button butGenearateReport;

    @FXML
    private TableColumn<EmployeeTm, String> colAddress;

    @FXML
    private TableColumn<EmployeeTm, String> colContact;

    @FXML
    private TableColumn<EmployeeTm, String> colEmail;

    @FXML
    private TableColumn<EmployeeTm, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeTm, String> colName;

    @FXML
    private TableColumn<EmployeeTm, String> colRole;

    @FXML
    private TableColumn<EmployeeTm, Double> colSalary;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private TableView<EmployeeTm> tbEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtSalary;

    EmployeeBo employeeBo = (EmployeeBo) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    @FXML
    void OnActionbutGenearateReport(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        boolean result = employeeBo.delete(lblEmployeeId.getText());

        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Delete Successful").show();
            Reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Delete UnSuccessful").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String empId = lblEmployeeId.getText();
        String name = txtName.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if (name.isEmpty() || role.isEmpty() || email.isEmpty() || address.isEmpty() || contact.isEmpty() || txtSalary.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        double salary;

        try {
            salary = Double.parseDouble(txtSalary.getText());

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Salary must be a valid number").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String rolePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String addressPattern = "^[A-Za-z0-9 ]+$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String salaryPattern = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidRole = role.matches(rolePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(contactPattern);
        boolean isValidSalary = txtSalary.getText().matches(salaryPattern);

        if (!isValidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").showAndWait();
            return;
        }
        if (!isValidRole) {
            new Alert(Alert.AlertType.ERROR, "Invalid Role").showAndWait();
            return;
        }
        if (!isValidEmail) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").showAndWait();
            return;
        }
        if (!isValidAddress) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").showAndWait();
            return;
        }
        if (!isValidContact) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").showAndWait();
            return;
        }
        if (!isValidSalary) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary").showAndWait();
            return;
        }

        boolean isResult = employeeBo.save(new EmployeeDto(empId, name, role, email, address, contact, salary));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Save Successful").show();
            Reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Save UnSuccessful").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String empId = lblEmployeeId.getText();
        String name = txtName.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if (name.isEmpty() || role.isEmpty() || email.isEmpty() || address.isEmpty() || contact.isEmpty() || txtSalary.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        double salary;

        try {
            salary = Double.parseDouble(txtSalary.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Salary must be a valid number").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String rolePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String addressPattern = "^[A-Za-z0-9 ]+$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String salaryPattern = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidRole = role.matches(rolePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(contactPattern);
        boolean isValidSalary = txtSalary.getText().matches(salaryPattern);

        if (!isValidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").showAndWait();
            return;
        }
        if (!isValidRole) {
            new Alert(Alert.AlertType.ERROR, "Invalid Role").showAndWait();
            return;
        }
        if (!isValidEmail) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").showAndWait();
            return;
        }
        if (!isValidAddress) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").showAndWait();
            return;
        }
        if (!isValidContact) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").showAndWait();
            return;
        }
        if (!isValidSalary) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary").showAndWait();
            return;
        }

        boolean isResult = employeeBo.update(new EmployeeDto(empId, name, role, email, address, contact, salary));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Update Successful").show();
            Reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Update UnSuccessful").show();
        }
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        EmployeeTm employeeTm = tbEmployee.getSelectionModel().getSelectedItem();

        if (employeeTm != null) {
            lblEmployeeId.setText(employeeTm.getEmpId());
            txtName.setText(employeeTm.getName());
            txtRole.setText(employeeTm.getRole());
            txtEmail.setText(employeeTm.getEmail());
            txtAddress.setText(employeeTm.getAddress());
            txtContact.setText(employeeTm.getContact());
            txtSalary.setText(String.valueOf(employeeTm.getSalary()));
        }

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        Reset();
    }

    public void loadTable() throws SQLException {
        ArrayList<EmployeeDto> employeeDtos = employeeBo.getAll();

        ObservableList<EmployeeTm> employeeTms = FXCollections.observableArrayList();

        for (EmployeeDto employeeDto : employeeDtos) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employeeDto.getEmpId(),
                    employeeDto.getName(),
                    employeeDto.getRole(),
                    employeeDto.getEmail(),
                    employeeDto.getAddress(),
                    employeeDto.getContact(),
                    employeeDto.getSalary()
            );
            employeeTms.add(employeeTm);
        }
        tbEmployee.setItems(employeeTms);
    }

    public void getNextEmployeeId() throws SQLException {
        String empId = employeeBo.getNextId();
        lblEmployeeId.setText(empId);
    }

    public void Reset() throws SQLException {
        getNextEmployeeId();
        loadTable();

        txtName.setText("");
        txtName.setText("");
        txtRole.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtSalary.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            Reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
