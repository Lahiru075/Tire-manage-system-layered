package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PaymentBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.PaymentDao;
import lk.ijse.gdse.dto.PaymentDto;
import lk.ijse.gdse.entity.Payment;

import java.sql.SQLException;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    public String getNextId() throws SQLException {
        String lastId = paymentDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("P%03d", newIdIndex);
            return lastId;
        }

        return "P001";
    }

    public boolean save(PaymentDto paymentDto) throws SQLException {
//        return paymentDao.save(paymentDto);
        return paymentDao.save(new Payment(paymentDto.getPId(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus(), paymentDto.getPaymentMethod()));
    }

}
