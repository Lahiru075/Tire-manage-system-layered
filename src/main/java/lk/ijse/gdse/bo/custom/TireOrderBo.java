package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TireOrderBo extends SuperBo {
    boolean saveTireOrder(ArrayList<TireOrderDto> tireOrderDtos) throws SQLException;
    boolean save(TireOrderDto tireOrderDto) throws SQLException;
}
