package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ViewOrderBo;
import lk.ijse.gdse.dao.costom.QueryDao;
import lk.ijse.gdse.dao.costom.impl.QueryDaoImpl;
import lk.ijse.gdse.dto.OrderViewDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrderBoImpl implements ViewOrderBo {

    QueryDao queryDao = new QueryDaoImpl();

    public ArrayList<OrderViewDto> getAllOrders() throws SQLException {
        return queryDao.getAllOrders();
    }

    public ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException {
        return queryDao.searchByCustId(custId);
    }

    public ArrayList<OrderViewDto> searchByDayforOrders(String day1, String day2) throws SQLException {
        return queryDao.searchByDayforOrders(day1,day2);
    }
}
