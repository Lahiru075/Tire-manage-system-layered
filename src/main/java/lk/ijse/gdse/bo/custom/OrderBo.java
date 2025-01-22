package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.OrdersDto;
import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {
    boolean saveOrder(OrdersDto ordersDto, ArrayList<TireOrderDto> tireOrderDtos) throws SQLException;
    String getNextId() throws SQLException;

}
