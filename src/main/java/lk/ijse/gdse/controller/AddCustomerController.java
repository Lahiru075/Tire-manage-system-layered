package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBo;
import lk.ijse.gdse.dto.CustomerDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

//    CustomerDaoImpl customerModel = new CustomerDaoImpl();

    @FXML
    private Button butSave;

    @FXML
    private Label lblCustomerId;

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
    void butSaveOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();
        String custName = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (custName.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        boolean isResult = customerBo.save(new CustomerDto(customerId, custName, email, contact, address));

        if (isResult) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Save Successful").showAndWait();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Save UnSuccessful").showAndWait();
            reset();
        }
    }

    void getNextCustomerId() throws SQLException {
        String customerId = customerBo.getNextId();
        lblCustomerId.setText(customerId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getNextCustomerId();
        } catch (SQLException e) {
            System.out.println("Can't get next customer id");
        }
    }

    private void reset() throws SQLException {
        getNextCustomerId();

        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtAddress.setText("");
    }
}
