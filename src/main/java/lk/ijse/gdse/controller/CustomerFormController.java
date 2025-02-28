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
import lk.ijse.gdse.bo.custom.CustomerBo;
import lk.ijse.gdse.dto.CustomerDto;
import lk.ijse.gdse.dto.Tm.CustomerTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button butGenearateReport;

    @FXML
    private TableColumn<CustomerTm, String> colAddress;

    @FXML
    private TableColumn<CustomerTm, String> colContact;

    @FXML
    private TableColumn<CustomerTm, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTm, String> colEmail;

    @FXML
    private TableColumn<CustomerTm, String> colName;

    @FXML
    private Label lblCustomerId;

    @FXML
    private TableView<CustomerTm> tbCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    CustomerBo customerBo = (CustomerBo) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @FXML
    void onMouseClicked(MouseEvent event) {
        CustomerTm customerTm  = tbCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblCustomerId.setText(customerTm.getCusId());
            txtName.setText(customerTm.getCusName());
            txtEmail.setText(customerTm.getEmail());
            txtContact.setText(customerTm.getContact());
            txtAddress.setText(customerTm.getAddress());
        }
        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        boolean result = customerBo.delete(lblCustomerId.getText());

        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Delete Successful").show();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Delete UnSuccessful").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();
        String customerName = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (customerId.isEmpty() || customerName.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[A-Za-z0-9 ]+$";

        boolean isValidName = customerName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidContact = contact.matches(contactPattern);
        boolean isValidAddress = address.matches(addressPattern);

        if (!isValidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").showAndWait();
            return;
        }
        if (!isValidEmail) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").showAndWait();
            return;
        }
        if (!isValidContact) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").showAndWait();
            return;
        }
        if (!isValidAddress) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").showAndWait();
            return;
        }

        boolean isResult = customerBo.save(new CustomerDto(customerId, customerName, email, contact, address));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Save Successful").show();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Save UnSuccessful").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();
        String customerName = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (customerId.isEmpty() || customerName.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[A-Za-z0-9 ]+$";

        boolean isValidName = customerName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidContact = contact.matches(contactPattern);
        boolean isValidAddress = address.matches(addressPattern);

        if (!isValidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").showAndWait();
            return;
        }
        if (!isValidEmail) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").showAndWait();
            return;
        }
        if (!isValidContact) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").showAndWait();
            return;
        }
        if (!isValidAddress) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").showAndWait();
            return;
        }

        boolean result = customerBo.update(new CustomerDto(customerId, customerName, email, contact, address));

        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Update Successful").show();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Update UnSuccessful").show();
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        reset();
    }

    void getNextCustomerId() throws SQLException {
        String customerId = customerBo.getNextId();
        lblCustomerId.setText(customerId);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTableData() throws SQLException {
        ArrayList<CustomerDto> customerDTOS = customerBo.getAll();

        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDTOS) {
            CustomerTm customerTm = new CustomerTm(
                    customerDto.getCusId(),
                    customerDto.getCusName(),
                    customerDto.getEmail(),
                    customerDto.getContact(),
                    customerDto.getAddress()
            );
            customerTms.add(customerTm);
        }

        tbCustomer.setItems(customerTms);
    }

    private void reset() throws SQLException {
        getNextCustomerId();
        loadTableData();

        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtAddress.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }
}
