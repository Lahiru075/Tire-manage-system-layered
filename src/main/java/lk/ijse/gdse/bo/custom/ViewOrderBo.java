package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.OrderViewDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewOrderBo {
    ArrayList<OrderViewDto> searchByDayforOrders(String day1, String day2) throws SQLException;
    ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException;
    ArrayList<OrderViewDto> getAllOrders() throws SQLException;
}
