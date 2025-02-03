package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentBo extends SuperBo {
    String getNextId() throws SQLException;
    boolean save(PaymentDto paymentDto) throws SQLException;
}
