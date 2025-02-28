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
import lk.ijse.gdse.bo.custom.PlaceOrderBo;
import lk.ijse.gdse.dto.PlaceOrderDto;
import lk.ijse.gdse.dto.Tm.PlaceOrderTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TiresFormController implements Initializable {

//    PlaceOrderDaoImpl placeOrderDao = new PlaceOrderDaoImpl();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button butGenearateReport;

    @FXML
    private TableColumn<PlaceOrderTm, String> colBrand;

    @FXML
    private TableColumn<PlaceOrderTm, String> colModel;

    @FXML
    private TableColumn<PlaceOrderTm, Double> colPrice;

    @FXML
    private TableColumn<PlaceOrderTm, String> colSize;

    @FXML
    private TableColumn<PlaceOrderTm, String> colTireId;

    @FXML
    private TableColumn<PlaceOrderTm, Integer> colYear;

    @FXML
    private Label lbTireId;

    @FXML
    private TableView<PlaceOrderTm> tbTire;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtYear;

    PlaceOrderBo placeOrderBo = (PlaceOrderBo) BOFactory.getInstance().getBO(BOFactory.BOType.PLACE_ORDER);

    @FXML
    void OnActionbutGenearateReport(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String tireId = lbTireId.getText();

        boolean isDeleted = placeOrderBo.delete(tireId);

        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Tire Delete Successful").showAndWait();
            reset();
        }else{
            new Alert(Alert.AlertType.ERROR, "Tire Delete UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String tireId = lbTireId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String size = txtSize.getText();

        if (tireId.isEmpty() || brand.isEmpty() || model.isEmpty() || size.isEmpty() || txtYear.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Missing Information").showAndWait();
            return;
        }

        int year;
        double price;

        try {
            year = Integer.parseInt(txtYear.getText());
            price = Double.parseDouble(txtPrice.getText());
        }catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input: year or price").showAndWait();
            return;
        }

        String brandPattern = "^[a-zA-Z ]{2,30}$";
        String modelPattern = "^[a-zA-Z0-9-_]{2,20}$";
        String sizePattern = "^\\d{3}/\\d{2}(R\\d{2})?$";
        String yearPattern = "^\\d{4}$";
        String pricePattern = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

        boolean isValidBrand = brand.matches(brandPattern);
        boolean isValidModel = model.matches(modelPattern);
        boolean isValidSize = size.matches(sizePattern);
        boolean isValidYear = txtYear.getText().matches(yearPattern);
        boolean isValidPrice = txtPrice.getText().matches(pricePattern);

        if (!isValidBrand) {
            new Alert(Alert.AlertType.ERROR, "Invalid brand name").showAndWait();
            return;
        }
        if (!isValidModel) {
            new Alert(Alert.AlertType.ERROR, "Invalid model name").showAndWait();
            return;
        }
        if (!isValidSize) {
            new Alert(Alert.AlertType.ERROR, "Invalid size").showAndWait();
            return;
        }
        if (!isValidYear) {
            new Alert(Alert.AlertType.ERROR, "Invalid year").showAndWait();
            return;
        }
        if (!isValidPrice) {
            new Alert(Alert.AlertType.ERROR, "Invalid price").showAndWait();
            return;
        }


        PlaceOrderDto placeOrderDto = new PlaceOrderDto(tireId, brand, model, size, year, price);

        boolean isSaved = placeOrderBo.save(placeOrderDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Tire Save Successful").showAndWait();
            reset();
        }else{
            new Alert(Alert.AlertType.ERROR, "Tire Save UnSuccessful").showAndWait();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String tireId = lbTireId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String size = txtSize.getText();

        if (tireId.isEmpty() || brand.isEmpty() || model.isEmpty() || size.isEmpty() || txtYear.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Missing Information").showAndWait();
            return;
        }

        int year;
        double price;

        try {
            year = Integer.parseInt(txtYear.getText());
            price = Double.parseDouble(txtPrice.getText());
        }catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input: year or price").showAndWait();
            return;
        }

        String brandPattern = "^[a-zA-Z ]{2,30}$";
        String modelPattern = "^[a-zA-Z0-9-_]{2,20}$";
        String sizePattern = "^\\d{3}(\\/(\\d{1,3}|R\\d{2}))?(R\\d{2})?(\\s?[0-9]{2,3}[A-Z]{1,2})?$";
        String yearPattern = "^\\d{4}$";
        String pricePattern = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

        boolean isValidBrand = brand.matches(brandPattern);
        boolean isValidModel = model.matches(modelPattern);
        boolean isValidSize = size.matches(sizePattern);
        boolean isValidYear = txtYear.getText().matches(yearPattern);
        boolean isValidPrice = txtPrice.getText().matches(pricePattern);

        if (!isValidBrand) {
            new Alert(Alert.AlertType.ERROR, "Invalid brand name").showAndWait();
            return;
        }
        if (!isValidModel) {
            new Alert(Alert.AlertType.ERROR, "Invalid model name").showAndWait();
            return;
        }
        if (!isValidSize) {
            new Alert(Alert.AlertType.ERROR, "Invalid size").showAndWait();
            return;
        }
        if (!isValidYear) {
            new Alert(Alert.AlertType.ERROR, "Invalid year").showAndWait();
            return;
        }
        if (!isValidPrice) {
            new Alert(Alert.AlertType.ERROR, "Invalid price").showAndWait();
            return;
        }

        boolean isUpdate = placeOrderBo.update(new PlaceOrderDto(tireId, brand, model, size, year, price));

        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Tire Update Successful").showAndWait();
            reset();
        }else{
            new Alert(Alert.AlertType.ERROR, "Tire Update UnSuccessful").showAndWait();
        }
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        PlaceOrderTm placeOrderTm = tbTire.getSelectionModel().getSelectedItem();
        if (placeOrderTm != null) {
            lbTireId.setText(placeOrderTm.getTireId());
            txtBrand.setText(placeOrderTm.getTireBrand());
            txtModel.setText(placeOrderTm.getTireModel());
            txtPrice.setText(String.valueOf(placeOrderTm.getTirePrice()));
            txtSize.setText(placeOrderTm.getTireSize());
            txtYear.setText(String.valueOf(placeOrderTm.getYear()));
        }

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        reset();
    }

    private void loadAllTires() throws SQLException {
        ArrayList<PlaceOrderDto> allTires = placeOrderBo.getAll();

        ObservableList<PlaceOrderTm> placeOrderTms = FXCollections.observableArrayList();

        for (PlaceOrderDto placeOrderDto : allTires) {
            PlaceOrderTm placeOrderTm = new PlaceOrderTm(
                    placeOrderDto.getTireId(),
                    placeOrderDto.getTireBrand(),
                    placeOrderDto.getTireModel(),
                    placeOrderDto.getTireSize(),
                    placeOrderDto.getYear(),
                    placeOrderDto.getTirePrice()
            );
            placeOrderTms.add(placeOrderTm);
        }
        tbTire.setItems(placeOrderTms);
    }

    private void getNextTireId() throws SQLException {
        String nextTireId = placeOrderBo.getNextId();
        lbTireId.setText(nextTireId);
    }

    private void reset() throws SQLException {
        loadAllTires();
        getNextTireId();

        txtBrand.setText("");
        txtModel.setText("");
        txtPrice.setText("");
        txtSize.setText("");
        txtYear.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTireId.setCellValueFactory(new PropertyValueFactory<>("tireId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("tireBrand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("tireModel"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("tireSize"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("tirePrice"));

        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
