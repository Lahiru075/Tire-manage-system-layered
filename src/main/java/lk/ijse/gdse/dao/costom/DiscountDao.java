package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.DiscountDto;

import java.sql.SQLException;

public interface DiscountDao {
    String getNextDiscId() throws SQLException;
    boolean addDiscount(DiscountDto discountDto) throws SQLException;
}
