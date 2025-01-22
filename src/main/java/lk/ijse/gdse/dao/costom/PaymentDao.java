package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.PaymentDto;
import lk.ijse.gdse.entity.Payment;

import java.sql.SQLException;

public interface PaymentDao extends CrudDao<Payment> {
    //String getNextPaymentId() throws SQLException;
    //boolean addPayment(PaymentDto paymentDto) throws SQLException;
}
