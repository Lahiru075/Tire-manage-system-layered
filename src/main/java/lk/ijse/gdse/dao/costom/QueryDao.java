package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.SuperDao;
import lk.ijse.gdse.entity.OrderView;
import lk.ijse.gdse.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    ArrayList<Report> getAllReport() throws SQLException;
    ArrayList<Report> searchByDay(String day1, String day2) throws SQLException;
    ArrayList<OrderView> getAllOrders() throws SQLException;
    ArrayList<OrderView> searchByDayforOrders(String day1, String day2) throws SQLException;
    ArrayList<OrderView> searchByCustId(String custId) throws SQLException;
}
