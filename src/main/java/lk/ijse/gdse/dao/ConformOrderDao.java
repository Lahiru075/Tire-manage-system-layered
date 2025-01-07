package lk.ijse.gdse.dao;

import java.sql.SQLException;

public interface ConformOrderDao {
    String getNextPaymentId() throws SQLException;
}
