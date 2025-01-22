package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.OrderViewDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderViewDao {
//    ArrayList<OrderViewDto> getAllOrders() throws SQLException;
//    ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException;
//    ArrayList<OrderViewDto> searchByDay(String day1, String day2) throws SQLException;
    ArrayList<String> getAllCustomerIds() throws SQLException;
}
