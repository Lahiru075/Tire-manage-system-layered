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
import lk.ijse.gdse.bo.custom.SupplierOrderBo;
import lk.ijse.gdse.dto.SupplierOrderDto;
import lk.ijse.gdse.dto.Tm.SupplierOrderTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewSupplierOrderController implements Initializable {

//    SupplierOrderDaoImpl supplierOrderDao = new SupplierOrderDaoImpl();

    @FXML
    private Button butAddToStock;

    @FXML
    private Button butDelete;

    @FXML
    private Button butUpdate;

    @FXML
    private ComboBox<String> cmbEmployee;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbStockId;

    @FXML
    private ComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<SupplierOrderTm, String> colBrand;

    @FXML
    private TableColumn<SupplierOrderTm, String> colEmpId;

    @FXML
    private TableColumn<SupplierOrderTm, String> colModel;

    @FXML
    private TableColumn<SupplierOrderTm, Date> colOrderDate;

    @FXML
    private TableColumn<SupplierOrderTm, String> colOrderId;

    @FXML
    private TableColumn<SupplierOrderTm, String> colOrderSize;

    @FXML
    private TableColumn<SupplierOrderTm, String> colOrderStatus;

    @FXML
    private TableColumn<SupplierOrderTm, Double> colOrderTotal;

    @FXML
    private TableColumn<SupplierOrderTm, Integer> colQty;

    @FXML
    private TableColumn<SupplierOrderTm, Date> colRequestDate;

    @FXML
    private TableColumn<SupplierOrderTm, String> colStockId;

    @FXML
    private TableColumn<SupplierOrderTm, String> colSupplierId;

    @FXML
    private TableColumn<SupplierOrderTm, Integer> colYear;

    @FXML
    private DatePicker dateOderDate;

    @FXML
    private DatePicker dateReuestDate;

    @FXML
    private Label lbOrderId;

    @FXML
    private TableView<SupplierOrderTm> tbOrder;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    private TextField txtYear;

    SupplierOrderBo supplierOrderBo = (SupplierOrderBo) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER_ORDER);

    @FXML
    void butAddToStockOnAction(ActionEvent event) throws SQLException {
        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();
        supplierOrderDto.setOrderId(lbOrderId.getText());
        supplierOrderDto.setSupId(cmbSupplierId.getValue());
        supplierOrderDto.setEmployeeId(cmbEmployee.getValue());
        supplierOrderDto.setStockId(cmbStockId.getValue());
        supplierOrderDto.setTireModel(txtModel.getText());
        supplierOrderDto.setTireBrand(txtBrand.getText());
        supplierOrderDto.setOrderSize(txtSize.getText());
        supplierOrderDto.setOrderStatus(cmbStatus.getValue());

        if (lbOrderId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select Order").showAndWait();
            return;
        }

        LocalDate date1 = dateOderDate.getValue();
        LocalDate date2 = dateReuestDate.getValue();

        if (date1 == null || date2 == null) {
            new Alert(Alert.AlertType.ERROR, "Select Date").showAndWait();
            return;
        }

        supplierOrderDto.setOrderDate(Date.valueOf(dateOderDate.getValue()));
        supplierOrderDto.setRequestDate(Date.valueOf(dateReuestDate.getValue()));

        try {
            supplierOrderDto.setQty(Integer.parseInt(txtQty.getText()));
            supplierOrderDto.setTotal(Double.parseDouble(txtTotalAmount.getText()));
            supplierOrderDto.setYear(Integer.parseInt(txtYear.getText()));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Enter Valid Number").showAndWait();
            return;
        }

        if (supplierOrderDto.getOrderStatus().equals("Completed")) {
            new Alert(Alert.AlertType.ERROR, "Completed Order Can't Add To Stock").showAndWait();
            return;
        }

        boolean isAdd = supplierOrderBo.addSupplierOrder(supplierOrderDto);

        if (isAdd) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Order Add Successful").showAndWait();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Order Add UnSuccessful").showAndWait();
        }
    }

    @FXML
    void butDeleteOnAction(ActionEvent event) throws SQLException {
        String orderId = lbOrderId.getText();
        String stockId = cmbStockId.getValue();

        boolean isDelete = supplierOrderBo.deleteSupplierOrder(orderId, stockId);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Order Delete Successful").showAndWait();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Order Delete UnSuccessful").showAndWait();
        }
    }

    @FXML
    void butUpdateOnAction(ActionEvent event) throws SQLException {
//        String orderId = lbOrderId.getText();
//        String stockId = cmbStockId.getValue();
//        String supId = cmbSupplierId.getValue();
//        String employeeId = cmbEmployee.getValue();
//        Date orderDate = Date.valueOf(dateOderDate.getValue());
//        int year = Integer.parseInt(txtYear.getText());
//        Date requestDate = Date.valueOf(dateReuestDate.getValue());
//        String model = txtModel.getText();
//        String brand = txtBrand.getText();
//        String status = cmbStatus.getValue();
//        String orderSize = txtSize.getText();
//        double total = Double.parseDouble(txtTotalAmount.getText());
//        int qty = Integer.parseInt(txtQty.getText());

        String orderId = lbOrderId.getText();
        String stockId = cmbStockId.getValue();
        String supId = cmbSupplierId.getValue();
        String employeeId = cmbEmployee.getValue();
        String status = cmbStatus.getValue();
        String orderSize = txtSize.getText();
        String model = txtModel.getText();
        String brand = txtBrand.getText();

        LocalDate date1 = dateOderDate.getValue();
        LocalDate date2 = dateReuestDate.getValue();

        if (orderId.isEmpty() || stockId == null || supId == null || employeeId == null ||
                date1 == null || date2 == null || status.isEmpty() ||
                orderSize == null || txtYear.getText().isEmpty() || txtTotalAmount.getText().isEmpty() ||
                txtQty.getText().isEmpty() || model == null || brand == null) {

            new Alert(Alert.AlertType.ERROR, "Empty Fields").showAndWait();
            return;
        }

        int year;
        double total;
        int qty;

        try {
            year = Integer.parseInt(txtYear.getText());
            total = Double.parseDouble(txtTotalAmount.getText());
            qty = Integer.parseInt(txtQty.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled correctly.").showAndWait();
            return;
        }

        Date orderDate = Date.valueOf(dateOderDate.getValue());
        Date requestDate = Date.valueOf(dateReuestDate.getValue());

        String qtyPattern = "^[1-9][0-9]*$";
        String brandPattern = "^[a-zA-Z ]{2,30}$";
        String modelPattern = "^[a-zA-Z0-9-_]{2,20}$";
        String sizePattern = "^\\d{3}(\\/(\\d{1,3}|R\\d{2}))?(R\\d{2})?(\\s?[0-9]{2,3}[A-Z]{1,2})?$";
        String yearPattern = "^\\d{4}$";
        String amountPattern = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!model.matches(modelPattern)) {
            new Alert(Alert.AlertType.ERROR, "Tire Model must be filled correctly.").showAndWait();
            return;
        }

        if (!brand.matches(brandPattern)) {
            new Alert(Alert.AlertType.ERROR, "Tire Brand must be filled correctly.").showAndWait();
            return;
        }

        if (!orderSize.matches(sizePattern)) {
            new Alert(Alert.AlertType.ERROR, "Order Size must be filled correctly.").showAndWait();
            return;
        }

        if (!txtYear.getText().matches(yearPattern)) {
            new Alert(Alert.AlertType.ERROR, "Year must be filled correctly.").showAndWait();
            return;
        }

        if (!txtTotalAmount.getText().matches(amountPattern)) {
            new Alert(Alert.AlertType.ERROR, "Total Amount must be filled correctly.").showAndWait();
            return;
        }

        if (!txtQty.getText().matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Quantity must be filled correctly.").showAndWait();
            return;
        }

        if (!orderDate.toString().matches(datePattern)) {
            new Alert(Alert.AlertType.ERROR, "Order Date must be filled correctly.").showAndWait();
            return;
        }

        if (!requestDate.toString().matches(datePattern)) {
            new Alert(Alert.AlertType.ERROR, "Request Date must be filled correctly.").showAndWait();
            return;
        }

        if (!date1.isBefore(date2)) {
            new Alert(Alert.AlertType.ERROR, "The first day must be before the second day.").showAndWait();
            return;
        }

        SupplierOrderDto supplierOrderDto = new SupplierOrderDto(
                orderId,
                stockId,
                supId,
                employeeId,
                orderDate,
                year,
                requestDate,
                model,
                brand,
                status,
                orderSize,
                total,
                qty
        );

        boolean isUpdate = supplierOrderBo.update(supplierOrderDto);

        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Order Update Successful").showAndWait();
            reset();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Order Update UnSuccessful").showAndWait();
        }
    }

    @FXML
    void OnMouseClick(MouseEvent event) {

        SupplierOrderTm supplierOrderTm = tbOrder.getSelectionModel().getSelectedItem();

        if (supplierOrderTm != null) {
            lbOrderId.setText(supplierOrderTm.getOrderId());
            cmbStockId.setValue(supplierOrderTm.getStockId());
            cmbSupplierId.setValue(supplierOrderTm.getSupId());
            cmbEmployee.setValue(supplierOrderTm.getEmployeeId());
            dateOderDate.setValue(supplierOrderTm.getOrderDate().toLocalDate());
            txtYear.setText(String.valueOf(supplierOrderTm.getYear()));
            dateReuestDate.setValue(supplierOrderTm.getRequestDate().toLocalDate());
            txtModel.setText(supplierOrderTm.getTireModel());
            txtBrand.setText(supplierOrderTm.getTireBrand());
            cmbStatus.setValue(supplierOrderTm.getOrderStatus());
            txtSize.setText(supplierOrderTm.getOrderSize());
            txtTotalAmount.setText(String.valueOf(supplierOrderTm.getTotal()));
            txtQty.setText(String.valueOf(supplierOrderTm.getQty()));
        }

        butDelete.setDisable(false);
        butUpdate.setDisable(false);
        butAddToStock.setDisable(false);
    }

    private void getAllSupplierOrders() throws SQLException {
        ArrayList<SupplierOrderDto> supplierOrderDtos = supplierOrderBo.getAll();

        ObservableList<SupplierOrderTm> supplierOrderTms = FXCollections.observableArrayList();

        for (SupplierOrderDto supplierOrderDto : supplierOrderDtos) {
            SupplierOrderTm supplierOrderTm = new SupplierOrderTm();

            supplierOrderTm.setOrderId(supplierOrderDto.getOrderId());
            supplierOrderTm.setStockId(supplierOrderDto.getStockId());
            supplierOrderTm.setSupId(supplierOrderDto.getSupId());
            supplierOrderTm.setEmployeeId(supplierOrderDto.getEmployeeId());
            supplierOrderTm.setQty(supplierOrderDto.getQty());
            supplierOrderTm.setTireModel(supplierOrderDto.getTireModel());
            supplierOrderTm.setTireBrand(supplierOrderDto.getTireBrand());
            supplierOrderTm.setOrderDate(supplierOrderDto.getOrderDate());
            supplierOrderTm.setYear(supplierOrderDto.getYear());
            supplierOrderTm.setRequestDate(supplierOrderDto.getRequestDate());
            supplierOrderTm.setTotal(supplierOrderDto.getTotal());
            supplierOrderTm.setOrderSize(supplierOrderDto.getOrderSize());
            supplierOrderTm.setOrderStatus(supplierOrderDto.getOrderStatus());

            supplierOrderTms.add(supplierOrderTm);
        }

        tbOrder.setItems(supplierOrderTms);

    }

    private void reset() throws SQLException {
        getAllSupplierOrders();

        lbOrderId.setText("");
        cmbStockId.setValue("");
        cmbSupplierId.setValue("");
        cmbEmployee.setValue("");
        dateOderDate.setValue(null);
        txtYear.setText("");
        dateReuestDate.setValue(null);
        txtModel.setText("");
        txtBrand.setText("");
        cmbStatus.setValue("");
        txtSize.setText("");
        txtTotalAmount.setText("");
        txtQty.setText("");
    }

    private void configureDatePicker() {
        dateOderDate.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(java.time.LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(java.time.LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        dateReuestDate.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(java.time.LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(java.time.LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colRequestDate.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("tireBrand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("tireModel"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        colOrderSize.setCellValueFactory(new PropertyValueFactory<>("orderSize"));
        colOrderTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        butDelete.setDisable(true);
        butUpdate.setDisable(true);
        butAddToStock.setDisable(true);

        try {
            reset();
            configureDatePicker();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
