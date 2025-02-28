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
import lk.ijse.gdse.bo.custom.SupplierBo;
import lk.ijse.gdse.dto.SupplierDto;
import lk.ijse.gdse.dto.Tm.SupplierTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

//    private SupplierDaoImpl supplierDao = new SupplierDaoImpl();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button butGenearateReport;

    @FXML
    private TableColumn<SupplierTm, String> colAddress;

    @FXML
    private TableColumn<SupplierTm, String> colContact;

    @FXML
    private TableColumn<SupplierTm, String> colEmail;

    @FXML
    private TableColumn<SupplierTm, String> colName;

    @FXML
    private TableColumn<SupplierTm, String> colSPId;

    @FXML
    private Label labSupplier;

    @FXML
    private TableView<SupplierTm> tbSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    SupplierBo supplierBo = (SupplierBo) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    @FXML
    void OnActionbutGenearateReport(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        boolean isResult = supplierBo.delete(labSupplier.getText());

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Delete Successful").showAndWait();
            reset();
        }else{
            new Alert(Alert.AlertType.ERROR, "Supplier Delete UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String supId = labSupplier.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (supId.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[A-Za-z0-9 ]+$";

        boolean isValidName = name.matches(namePattern);
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

        boolean isResult = supplierBo.save(new SupplierDto(supId, name, email, contact, address));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Save Successful").showAndWait();
            reset();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier Save UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String supId = labSupplier.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (supId.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[A-Za-z0-9 ]+$";

        boolean isValidName = name.matches(namePattern);
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

        boolean isResult = supplierBo.update(new SupplierDto(supId, name, email, contact, address));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Update Successful").showAndWait();
            reset();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier Update UnSuccessful").showAndWait();
        }
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        SupplierTm supplierTm = tbSupplier.getSelectionModel().getSelectedItem();

        if (supplierTm != null) {
            labSupplier.setText(supplierTm.getSupId());
            txtName.setText(supplierTm.getName());
            txtEmail.setText(supplierTm.getEmail());
            txtContact.setText(supplierTm.getContact());
            txtAddress.setText(supplierTm.getAddress());
        }

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        reset();
    }

    private void loadTableData() throws SQLException {
        ArrayList<SupplierDto> supplierDTOS = supplierBo.getAll();

        ObservableList<SupplierTm> supplierTms = FXCollections.observableArrayList();

        for (SupplierDto supplierDto : supplierDTOS) {
            SupplierTm supplierTm = new SupplierTm(
                    supplierDto.getSupId(),
                    supplierDto.getName(),
                    supplierDto.getEmail(),
                    supplierDto.getContact(),
                    supplierDto.getAddress()
            );
            supplierTms.add(supplierTm);
        }

        tbSupplier.setItems(supplierTms);
    }

    private void reset() throws SQLException {
        loadTableData();
        getNextSupplierId();

        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtAddress.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void getNextSupplierId() throws SQLException {
        String supplierId = supplierBo.getNextId();
        labSupplier.setText(supplierId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSPId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
