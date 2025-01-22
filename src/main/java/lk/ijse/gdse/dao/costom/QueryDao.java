package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.OrderViewDto;
import lk.ijse.gdse.dto.ReportDto;
import lk.ijse.gdse.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    ArrayList<Report> getAllReport() throws SQLException;
    ArrayList<Report> searchByDay(String day1, String day2) throws SQLException;
    ArrayList<OrderViewDto> getAllOrders() throws SQLException;
    ArrayList<OrderViewDto> searchByDayforOrders(String day1, String day2) throws SQLException;
    ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException;
}
