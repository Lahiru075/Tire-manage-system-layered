package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.PaymentDao;
import lk.ijse.gdse.entity.Payment;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select pId from payment order by pId desc limit 1");

        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException {
        return null;
    }

    public boolean save(Payment payment) throws SQLException {
        return CrudUtil.execute("insert into payment values (?,?,?,?,?)",
                payment.getPId(),
                payment.getAmount(),
                payment.getDate(),
                payment.getStatus(),
                payment.getPaymentMethod()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return false;
    }
}
