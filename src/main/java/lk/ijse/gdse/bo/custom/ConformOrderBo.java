package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;

import java.sql.SQLException;

public interface ConformOrderBo extends SuperBo {
    String getNextId() throws SQLException;
}
