package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.*;
import lk.ijse.gdse.bo.custom.CustomerBo;
import lk.ijse.gdse.bo.custom.OrderBo;
import lk.ijse.gdse.bo.custom.PlaceOrderBo;
import lk.ijse.gdse.bo.custom.StockBo;
import lk.ijse.gdse.dao.costom.EmployeeDao;
import lk.ijse.gdse.dao.costom.impl.EmployeeDaoImpl;
import lk.ijse.gdse.dao.costom.impl.PlaceOrderDaoImpl;
import lk.ijse.gdse.dao.costom.impl.StockDaoImpl;
import lk.ijse.gdse.dto.*;
import lk.ijse.gdse.dto.Tm.CartTm;
import lk.ijse.gdse.dto.Tm.PlaceOrderTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

//    PlaceOrderDaoImpl placeOrderModel = new PlaceOrderDaoImpl();
//    CustomerDaoImpl customerDao = new CustomerDaoImpl();
//    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
//    OrderDaoImpl orderDao = new OrderDaoImpl();

    private final ObservableList<CartTm> cartTms = FXCollections.observableArrayList();

    private OrdersDto ordersDto = new OrdersDto();
    private TireOrderDto tireOrderDto = new TireOrderDto();
//    private StockDaoImpl stockDao = new StockDaoImpl();

    private double total;

    @FXML
    private TableColumn<PlaceOrderTm, String> colBrand;

    @FXML
    private TableColumn<PlaceOrderTm, String> colModel;

    @FXML
    private TableColumn<PlaceOrderTm, String> colPrice;

    @FXML
    private TableColumn<PlaceOrderTm, String> colSize;

    @FXML
    private TableColumn<PlaceOrderTm, String> colTireId;

    @FXML
    private TableColumn<PlaceOrderTm, String> colYear;

    @FXML
    private TableView<PlaceOrderTm> tireTable;

    @FXML
    private TableView<CartTm> tabCart;

    @FXML
    private ComboBox<String> empCombo;

    @FXML
    private TableColumn<CartTm, String> colTireId1;

    @FXML
    private TableColumn<CartTm, Integer> colQty;

    @FXML
    private TableColumn<CartTm, String> colDesc;

    @FXML
    private TableColumn<CartTm, Double> colPrice1;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TextField txtTireId;

    @FXML
    private TextField textContact;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchBrand;

    @FXML
    private Label txtTotal;

    @FXML
    private Button butCheck;

    @FXML
    private Label labDate;

    @FXML
    private Label labOrderId;

    @FXML
    private Label labQty;

    CustomerBo customerBo = (CustomerBo) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    PlaceOrderBo placeOrderBo = (PlaceOrderBo) BOFactory.getInstance().getBO(BOFactory.BOType.PLACE_ORDER);
    OrderBo orderBo = (OrderBo) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);
    StockBo stockBo = (StockBo) BOFactory.getInstance().getBO(BOFactory.BOType.STOCK);

    public Label getDate(){
        return labDate;
    }

    public Label getLabOrderId(){
        System.out.println();
        return labOrderId;
    }

    public ObservableList<CartTm> getCartTms() {
        System.out.println();
        return cartTms;
    }

    public OrdersDto getOrdersDto() {
        System.out.println();
        return ordersDto;
    }

    public TireOrderDto getTireOrderDto() {
        System.out.println();
        return tireOrderDto;
    }

    public double getTotal() {
        System.out.println();
        return total;
    }

    private void set1CellValues() {
        colTireId1.setCellValueFactory(new PropertyValueFactory<>("tireId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPrice1.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("butRemove"));

        tabCart.setItems(cartTms);
    }


    void loadEmpIds() throws SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        ArrayList<String> empId = employeeDao.getAllEmployeesContact();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (String id : empId) {
            observableList.addAll(id);
        }
        empCombo.setItems(observableList);
    }

    @FXML
    void butCheckOnAction(ActionEvent event) throws SQLException, IOException {
        String contact = textContact.getText();
        boolean check = customerBo.checkCustomer(contact);

        if (check) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Exist").showAndWait();
        } else {
            Parent load = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Customer");
            stage.setResizable(false);
            stage.showAndWait();

            String contact1 = customerBo.getCustomerContactNo();
            textContact.setText(contact1);
        }
    }

    @FXML
    void MouseClicked(MouseEvent event) throws SQLException {
        PlaceOrderTm placeOrderTm = tireTable.getSelectionModel().getSelectedItem();
        if (placeOrderTm != null) {
            txtTireId.setText(placeOrderTm.getTireId());
        }

        String tireId = txtTireId.getText();

        PlaceOrderDaoImpl placeOrderDao = new PlaceOrderDaoImpl();
        int qty = placeOrderDao.getQty(tireId);
        labQty.setText(String.valueOf(qty));
    }

    private void loadTable() throws SQLException {
        ArrayList<PlaceOrderDto> placeOrderDTOS = placeOrderBo.getAll();

        ObservableList<PlaceOrderTm> placeOrderTms = FXCollections.observableArrayList();

        for (PlaceOrderDto placeOrderDto : placeOrderDTOS) {
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

        tireTable.setItems(placeOrderTms);

    }

    @FXML
    void butAddOnAction(ActionEvent event) throws SQLException {
        String tireId = txtTireId.getText();
        double totalPrice = 0;

        if (tireId == null || tireId.isEmpty() || txtQty.getText() == null || txtQty.getText().isEmpty() || textContact == null || textContact.getText().isEmpty() || empCombo == null || empCombo.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Missing Information").show();
            return;
        }

        PlaceOrderDto placeOrderDto = placeOrderBo.getTire(tireId);

        String description = placeOrderDto.getTireBrand() + " " + placeOrderDto.getTireModel() + " " + placeOrderDto.getTireSize();
        double price = placeOrderDto.getTirePrice();

        int qty = 0;

        try {
            qty = Integer.parseInt(txtQty.getText());
            if (qty == 0) {
                new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter a valid number.").show();
                txtQty.clear();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter a valid number.").show();
            txtQty.clear();
            return;
        }

        int stockQty = Integer.parseInt(labQty.getText());

        if (qty > stockQty) {
            new Alert(Alert.AlertType.ERROR, "Not enough stock").show();
            return;
        }

        txtQty.setText("");

        double newPrice = price * qty;

        if (cartTms.isEmpty()) {
            txtTotal.setText(String.valueOf(newPrice));

        } else {
            totalPrice = Double.parseDouble(txtTotal.getText());
        }

        for (CartTm cartTm : cartTms) {
            if (cartTm.getTireId().equals(tireId)) {
                int newQty = cartTm.getQty() + qty;

                // check quantity
                int checkQty = stockBo.getQty(description);

                if (newQty > checkQty) {
                    new Alert(Alert.AlertType.ERROR, "Not enough stock").showAndWait();
                    return;
                }

                cartTm.setQty(newQty);

                double updatedPrice = cartTm.getPrice() + (qty * price);
                cartTm.setPrice(updatedPrice);

                totalPrice += qty * price;
                txtTotal.setText(String.valueOf(totalPrice));
                this.total = Double.parseDouble(txtTotal.getText());

                tabCart.refresh();
                return;
            }
        }

        Button button = new Button("Remove");
        CartTm cartTm = new CartTm(
                tireId,
                qty,
                description,
                newPrice,
                button
        );

        button.setOnAction(actionEvent -> {
            cartTms.remove(cartTm);
            tabCart.refresh();

            double newTotalPrice = 0;
            for (CartTm item : cartTms) {
                newTotalPrice += item.getPrice();
            }

            txtTotal.setText(String.valueOf(newTotalPrice));
            this.total = Double.parseDouble(txtTotal.getText());

            if (cartTms.isEmpty()) {
                txtTotal.setText("0.0");
                this.total = 0.0;
            }
        });




        cartTms.add(cartTm);
        totalPrice += newPrice;
        txtTotal.setText(String.valueOf(totalPrice));
        this.total = Double.parseDouble(txtTotal.getText());
        tabCart.refresh();

        ordersDto.setOrderId(labOrderId.getText());
        ordersDto.setDate(labDate.getText());
        String custId = customerBo.getCustId(textContact.getText());
        ordersDto.setCustId(custId);

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        String empId = employeeDao.getEmpId(empCombo.getValue());

        ordersDto.setEmpId(empId);

        txtSearchBrand.setText("");
        loadTable();

        int realQty = Integer.parseInt(labQty.getText());
        StockDaoImpl stockDao = new StockDaoImpl();
        String recod = stockDao.getRecodLevel(txtTireId.getText());

        int level = 0;
        if (recod != null) {
            level = Integer.parseInt(recod);
        }

        if (level >= realQty) {
            new Alert(Alert.AlertType.INFORMATION, "Low Stock").showAndWait();
        }

//        for (CartTm cartTm1 : cartTms) {
//            tireOrderDto.setOrderId(labOrderId.getText());
//        }

    }


    @FXML
    void placeOrderOnAction(ActionEvent event) throws IOException {
        if (cartTms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "No items in the cart").showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConformOrder.fxml"));
        Parent root = loader.load();

        ConformOrderController conformOrderController1 = loader.getController();
        conformOrderController1.setPlaceOrderController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        Image image = new Image(getClass().getResourceAsStream("/images/tire.png"));
        stage.getIcons().add(image);
        stage.setTitle("Conform Order");
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void butSearchBrandOnAction(ActionEvent event) throws SQLException {
        String brand = txtSearchBrand.getText();

        if (brand == null || brand.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter Tire Brand").showAndWait();
            txtSearchBrand.setText("");
            return;
        }

        ArrayList<PlaceOrderDto> placeOrderDTOS = placeOrderBo.getTireByBrand(brand);

        if (placeOrderDTOS.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "No tire found").showAndWait();
            txtSearchBrand.setText("");
            return;
        }

        ObservableList<PlaceOrderTm> placeOrderTms = FXCollections.observableArrayList();

        for (PlaceOrderDto placeOrderDto : placeOrderDTOS) {
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

        tireTable.setItems(placeOrderTms);
        txtSearchBrand.setText("");
    }

    @FXML
    void butRefreshOnAction(ActionEvent event) throws SQLException {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTireId.setCellValueFactory(new PropertyValueFactory<>("tireId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("tireBrand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("tireModel"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("tireSize"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("tirePrice"));

        labDate.setText(LocalDate.now().toString());

        try {
            labOrderId.setText(orderBo.getNextId());
            loadEmpIds();
            loadTable();
            set1CellValues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() throws SQLException {
        loadTable();

        labQty.setText("");
        labOrderId.setText(orderBo.getNextId());
        txtTireId.setText("");
        txtQty.setText("");
        textContact.setText("");
        empCombo.setValue("");
        tabCart.getItems().clear();
        txtTotal.setText("");
        txtSearchBrand.setText("");

    }
}