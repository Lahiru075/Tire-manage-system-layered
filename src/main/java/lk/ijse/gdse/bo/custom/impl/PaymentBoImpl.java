package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PaymentBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.PaymentDao;
import lk.ijse.gdse.dao.costom.impl.PaymentDaoImpl;
import lk.ijse.gdse.dto.PaymentDto;
import lk.ijse.gdse.entity.Payment;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    public String getNextId() throws SQLException {
        return paymentDao.getNextId();
    }

    public boolean save(PaymentDto paymentDto) throws SQLException {
//        return paymentDao.save(paymentDto);
        return paymentDao.save(new Payment(paymentDto.getPId(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus(), paymentDto.getPaymentMethod()));
    }

}
