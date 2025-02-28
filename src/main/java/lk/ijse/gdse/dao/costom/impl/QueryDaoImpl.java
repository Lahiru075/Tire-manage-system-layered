package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.QueryDao;
import lk.ijse.gdse.entity.OrderView;
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

    public ArrayList<OrderView> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId");

        ArrayList<OrderView> orderViews = new ArrayList<>();
        while (rst.next()) {
            OrderView orderView = new OrderView();
            orderView.setOrderId(rst.getString(1));
            orderView.setDate(rst.getString(2));
            orderView.setCustId(rst.getString(3));
            orderView.setEmpId(rst.getString(4));
            orderView.setTireId(rst.getString(5));
            orderView.setDescription(rst.getString(6));
            orderView.setPayment_method(rst.getString(7));
            orderView.setQty(rst.getInt(8));
            orderView.setTotal_amount(rst.getDouble(9));

            orderViews.add(orderView);
        }
        return orderViews;
    }

    public ArrayList<OrderView> searchByCustId(String custId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId WHERE o.custId = ?", custId);

        ArrayList<OrderView> orderViews = new ArrayList<>();

        while (rst.next()) {
            OrderView orderView = new OrderView();
            orderView.setOrderId(rst.getString(1));
            orderView.setDate(rst.getString(2));
            orderView.setCustId(rst.getString(3));
            orderView.setEmpId(rst.getString(4));
            orderView.setTireId(rst.getString(5));
            orderView.setDescription(rst.getString(6));
            orderView.setPayment_method(rst.getString(7));
            orderView.setQty(rst.getInt(8));
            orderView.setTotal_amount(rst.getDouble(9));

            orderViews.add(orderView);
        }
        return orderViews;
    }

    public ArrayList<OrderView> searchByDayforOrders(String day1, String day2) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT o.orderId, o.date,  o.custId, o.empId, t.tireId,  t.description, t.payment_method, t.qty,  t.total_amount FROM   orders o JOIN tire_order t ON o.orderId = t.orderId WHERE o.date BETWEEN ? AND ?",day1,day2);

        ArrayList<OrderView> orderViews = new ArrayList<>();

        while (rst.next()) {
            OrderView orderView = new OrderView();
            orderView.setOrderId(rst.getString(1));
            orderView.setDate(rst.getString(2));
            orderView.setCustId(rst.getString(3));
            orderView.setEmpId(rst.getString(4));
            orderView.setTireId(rst.getString(5));
            orderView.setDescription(rst.getString(6));
            orderView.setPayment_method(rst.getString(7));
            orderView.setQty(rst.getInt(8));
            orderView.setTotal_amount(rst.getDouble(9));

            orderViews.add(orderView);
        }
        return orderViews;
    }


}
