package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.DiscountBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.DiscountDao;
import lk.ijse.gdse.dao.costom.impl.DiscountDaoImpl;
import lk.ijse.gdse.dto.DiscountDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscountBoImpl implements DiscountBo {

    DiscountDao discountDao = (DiscountDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DISCOUNT);

    public String getNextId() throws SQLException {
        return discountDao.getNextId();
    }

    public boolean save(DiscountDto discountDto) throws SQLException {
        return discountDao.save(discountDto);
    }
}
