package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TireOrderDao {
    boolean saveTireOrder(ArrayList<TireOrderDto> tireOrderDtos) throws SQLException;
    boolean saveTireOrderDetails(TireOrderDto tireOrderDto) throws SQLException;
}
