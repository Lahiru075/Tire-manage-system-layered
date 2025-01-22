package lk.ijse.gdse.bo.custom;

import java.sql.SQLException;

public interface ConformOrderBo extends SuperBo {
    String getNextId() throws SQLException;
}
