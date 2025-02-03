package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.ConformOrderDao;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConformOrderDaoImpl implements ConformOrderDao {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select pId from payment order by pId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException {
        return false;
    }


}
