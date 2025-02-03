package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.DiscountDto;

import java.sql.SQLException;

public interface DiscountBo extends SuperBo {
    String getNextId() throws SQLException;
    boolean save(DiscountDto discountDto) throws SQLException;
}
