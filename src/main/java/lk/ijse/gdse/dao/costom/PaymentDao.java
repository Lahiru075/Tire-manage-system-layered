package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentDao {
    String getNextPaymentId() throws SQLException;
    boolean addPayment(PaymentDto paymentDto) throws SQLException;
}
