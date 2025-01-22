package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.OrderBo;
import lk.ijse.gdse.bo.custom.TireOrderBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.OrderDao;
import lk.ijse.gdse.dao.costom.TireOrderDao;
import lk.ijse.gdse.dao.costom.impl.OrderDaoImpl;
import lk.ijse.gdse.dao.costom.impl.TireOrderDaoImpl;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.OrdersDto;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.Orders;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDao = (OrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    TireOrderBo tireOrderBo = new TireOrderBoImpl();

    public boolean saveOrder(OrdersDto ordersDto, ArrayList<TireOrderDto> tireOrderDtos) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

//            boolean isOrderSave = CrudUtil.execute("insert into orders values(?,?,?,?)",
//                    ordersDto.getOrderId(),
//                    ordersDto.getDate(),
//                    ordersDto.getCustId(),
//                    ordersDto.getEmpId()
//            );
            boolean isOrderSave = orderDao.save(new Orders(
                    ordersDto.getOrderId(),
                    ordersDto.getDate(),
                    ordersDto.getCustId(),
                    ordersDto.getEmpId()
            ));

            if (isOrderSave){

                boolean isOrderDetailSave = tireOrderBo.saveTireOrder(tireOrderDtos);

                if (isOrderDetailSave){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public String getNextId() throws SQLException {
        return orderDao.getNextId();
    }
}
