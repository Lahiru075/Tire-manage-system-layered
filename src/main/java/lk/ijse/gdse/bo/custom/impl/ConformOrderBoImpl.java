package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ConformOrderBo;
import lk.ijse.gdse.dao.costom.ConformOrderDao;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.impl.ConformOrderDaoImpl;

import java.sql.SQLException;

public class ConformOrderBoImpl implements ConformOrderBo {

    ConformOrderDao conformOrderDao = (ConformOrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CONFIRM_ORDER);

    public String getNextId() throws SQLException {
        return conformOrderDao.getNextId();
    }
}
