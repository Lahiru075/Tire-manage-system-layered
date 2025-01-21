package lk.ijse.gdse.dao.costom;

import java.sql.SQLException;

public interface ConformOrderDao {
    String getNextPaymentId() throws SQLException;
}
