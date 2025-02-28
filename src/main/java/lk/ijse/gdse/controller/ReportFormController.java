package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.ReportBo;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.ReportDto;
import lk.ijse.gdse.dto.Tm.ReportTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

//    ReportDaoImpl reportDao = new ReportDaoImpl();

    @FXML
    private Button butReset;

    @FXML
    private Button butSearch;

    @FXML
    private Button butGenarate;

    @FXML
    private TableColumn<ReportTm, String> colDate;

    @FXML
    private TableColumn<ReportTm, String> colDiscountAmount;

    @FXML
    private TableColumn<ReportTm, String> colDiscountId;

    @FXML
    private TableColumn<ReportTm, String> colPaymentAmount;

    @FXML
    private TableColumn<ReportTm, String> colPaymentId;

    @FXML
    private TableColumn<ReportTm, String> colPaymentMethod;

    @FXML
    private TableColumn<ReportTm, String> colPaymentStatus;

    @FXML
    private TableView<ReportTm> tbPayment;

    @FXML
    private TextField txtFirstDay;

    @FXML
    private TextField txtSecondDay;

    ReportBo reportBo = (ReportBo) BOFactory.getInstance().getBO(BOFactory.BOType.REPORT);

    @FXML
    void butResetOnAction(ActionEvent event) throws SQLException {
        reset();
    }

    @FXML
    void butSearchOnAction(ActionEvent event) throws SQLException {
        String day1 = txtFirstDay.getText();
        String day2 = txtSecondDay.getText();

        if (day1.isEmpty() || day2.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter both start and end days").showAndWait();
            return;
        }

        String dayPatten = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$";

        if (!day1.matches(dayPatten) || !day2.matches(dayPatten)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid date").showAndWait();
            return;
        }

        ArrayList<ReportDto> reportDtos = reportBo.searchByDay(day1, day2);

        ObservableList<ReportTm> reportTms = FXCollections.observableArrayList();

        for (ReportDto reportDto : reportDtos) {
            ReportTm reportTm = new ReportTm(
                    reportDto.getPaymentId(),
                    reportDto.getPaymentAmount(),
                    reportDto.getDate(),
                    reportDto.getPaymentStatus(),
                    reportDto.getDiscountId(),
                    reportDto.getDiscountAmount(),
                    reportDto.getPaymentMethod()
            );
            reportTms.add(reportTm);
        }

        tbPayment.setItems(reportTms);
    }

    private void getAllReport() throws SQLException {
        ArrayList<ReportDto> allReport = reportBo.getAllReport();

        ObservableList<ReportTm> reportTms = FXCollections.observableArrayList();

        for (ReportDto reportDto : allReport) {
            ReportTm reportTm = new ReportTm(
                    reportDto.getPaymentId(),
                    reportDto.getPaymentAmount(),
                    reportDto.getDate(),
                    reportDto.getPaymentStatus(),
                    reportDto.getDiscountId(),
                    reportDto.getDiscountAmount(),
                    reportDto.getPaymentMethod()
            );
            reportTms.add(reportTm);
        }

        tbPayment.setItems(reportTms);
    }

    private void reset() throws SQLException {
        getAllReport();

        txtFirstDay.setText("");
        txtSecondDay.setText("");
    }

    @FXML
    void butGenarateOnAction(ActionEvent event) {

        String day1 = txtFirstDay.getText();
        String day2 = txtSecondDay.getText();

        if (day1.isEmpty() || day2.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter both start and end days").showAndWait();
            return;
        }

        String dayPatten = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$";

        if (!day1.matches(dayPatten) || !day2.matches(dayPatten)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid date").showAndWait();
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/PaymentReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("dayOne",txtFirstDay.getText());
            parameters.put("dayTwo",txtSecondDay.getText());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        colDiscountAmount.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
