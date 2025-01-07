package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.OrdersDto;
import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao {
    boolean saveOrder(OrdersDto ordersDto, ArrayList<TireOrderDto> tireOrderDtos) throws SQLException;
}
