package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ConformOrderBo;
import lk.ijse.gdse.dao.costom.ConformOrderDao;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.DAOFactory.DAOType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConformOrderBoImpl implements ConformOrderBo {

    ConformOrderDao conformOrderDao = (ConformOrderDao) DAOFactory.getInstance().getDAO(DAOType.CONFIRM_ORDER);

    public String getNextId() throws SQLException {

        String lastId = conformOrderDao.getNextId();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("P%03d", newIdIndex);

            return lastId;
        }

        return "P001";
    }
}
