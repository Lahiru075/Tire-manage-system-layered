package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.QueryDao;
import lk.ijse.gdse.dto.OrderViewDto;
import lk.ijse.gdse.dto.ReportDto;
import lk.ijse.gdse.entity.Report;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {

    public ArrayList<Report> getAllReport() throws SQLException {
        ResultSet rst = CrudUtil.execute("select p.pId, p.amount, p.date, p.status, d.descId, d.amount, p.paymentMethod from payment p join discount d on p.pId = d.pId");

        ArrayList<Report> reports = new ArrayList<>();

        while (rst.next()) {
            Report report = new Report();
            report.setPaymentId(rst.getString(1));
            report.setPaymentAmount(rst.getDouble(2));
            report.setDate(rst.getString(3));
            report.setPaymentStatus(rst.getString(4));
            report.setDiscountId(rst.getString(5));
            report.setDiscountAmount(rst.getDouble(6));
            report.setPaymentMethod(rst.getString(7));

            reports.add(report);
        }
        return reports;
    }

    public ArrayList<Report> searchByDay(String day1, String day2) throws SQLException {
        ResultSet rst = CrudUtil.execute("select p.pId, p.amount, p.date, p.status, d.descId, d.amount, p.paymentMethod from payment p join discount d on p.pId = d.pId where p.date between ? and ?", day1, day2);

        ArrayList<Report> reports = new ArrayList<>();

        while (rst.next()) {
            Report report = new Report();
            report.setPaymentId(rst.getString(1));
            report.setPaymentAmount(rst.getDouble(2));
            report.setDate(rst.getString(3));
            report.setPaymentStatus(rst.getString(4));
            report.setDiscountId(rst.getString(5));
            report.setDiscountAmount(rst.getDouble(6));
            report.setPaymentMethod(rst.getString(7));

            reports.add(report);
        }
        return reports;
    }

    public ArrayList<OrderViewDto> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId");

        ArrayList<OrderViewDto> orderViewDTOS = new ArrayList<>();
        while (rst.next()) {
            OrderViewDto orderViewDto = new OrderViewDto();
            orderViewDto.setOrderId(rst.getString(1));
            orderViewDto.setDate(rst.getString(2));
            orderViewDto.setCustId(rst.getString(3));
            orderViewDto.setEmpId(rst.getString(4));
            orderViewDto.setTireId(rst.getString(5));
            orderViewDto.setDescription(rst.getString(6));
            orderViewDto.setPayment_method(rst.getString(7));
            orderViewDto.setQty(rst.getInt(8));
            orderViewDto.setTotal_amount(rst.getDouble(9));

            orderViewDTOS.add(orderViewDto);
        }
        return orderViewDTOS;
    }

    public ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId WHERE o.custId = ?", custId);

        ArrayList<OrderViewDto> orderViewDTOS = new ArrayList<>();

        while (rst.next()) {
            OrderViewDto orderViewDto = new OrderViewDto();
            orderViewDto.setOrderId(rst.getString(1));
            orderViewDto.setDate(rst.getString(2));
            orderViewDto.setCustId(rst.getString(3));
            orderViewDto.setEmpId(rst.getString(4));
            orderViewDto.setTireId(rst.getString(5));
            orderViewDto.setDescription(rst.getString(6));
            orderViewDto.setPayment_method(rst.getString(7));
            orderViewDto.setQty(rst.getInt(8));
            orderViewDto.setTotal_amount(rst.getDouble(9));

            orderViewDTOS.add(orderViewDto);
        }
        return orderViewDTOS;
    }

    public ArrayList<OrderViewDto> searchByDayforOrders(String day1, String day2) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId WHERE o.date BETWEEN ? AND ?",day1,day2);

        ArrayList<OrderViewDto> orderViewDTOS = new ArrayList<>();

        while (rst.next()) {
            OrderViewDto orderViewDto = new OrderViewDto();
            orderViewDto.setOrderId(rst.getString(1));
            orderViewDto.setDate(rst.getString(2));
            orderViewDto.setCustId(rst.getString(3));
            orderViewDto.setEmpId(rst.getString(4));
            orderViewDto.setTireId(rst.getString(5));
            orderViewDto.setDescription(rst.getString(6));
            orderViewDto.setPayment_method(rst.getString(7));
            orderViewDto.setQty(rst.getInt(8));
            orderViewDto.setTotal_amount(rst.getDouble(9));

            orderViewDTOS.add(orderViewDto);
        }
        return orderViewDTOS;
    }


}
