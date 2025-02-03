package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.DiscountBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.DiscountDao;
import lk.ijse.gdse.dto.DiscountDto;

import java.sql.SQLException;

public class DiscountBoImpl implements DiscountBo {

    DiscountDao discountDao = (DiscountDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DISCOUNT);

    public String getNextId() throws SQLException {
        String lastId = discountDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("D%03d", newIdIndex);
            return lastId;
        }

        return "D001";
    }

    public boolean save(DiscountDto discountDto) throws SQLException {
        return discountDao.save(discountDto);
    }
}
