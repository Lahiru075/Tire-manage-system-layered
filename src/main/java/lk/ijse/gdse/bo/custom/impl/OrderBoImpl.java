package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.OrderBo;
import lk.ijse.gdse.bo.custom.TireOrderBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.OrderDao;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.OrdersDto;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDao = (OrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    TireOrderBo tireOrderBo = (TireOrderBo) BOFactory.getInstance().getBO(BOFactory.BOType.TIRE_ORDER);

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
        String lastId = orderDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("O%03d", newIdIndex);
            return lastId;
        }

        return "O001";
    }
}
