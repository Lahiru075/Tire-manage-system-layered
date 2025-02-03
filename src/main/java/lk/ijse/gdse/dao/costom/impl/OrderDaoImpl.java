package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.OrderDao;
import lk.ijse.gdse.entity.Orders;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
//    private final TireOrderDaoImpl tireOrderDao = new TireOrderDaoImpl();

//    public  boolean saveOrder(Orders orders, ArrayList<TireOrderDto> tireOrderDtos) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            connection.setAutoCommit(false);
//
////            boolean isOrderSave = CrudUtil.execute("insert into orders values(?,?,?,?)",
////                    ordersDto.getOrderId(),
////                    ordersDto.getDate(),
////                    ordersDto.getCustId(),
////                    ordersDto.getEmpId()
////            );
////
////            if (isOrderSave){
//////                System.out.println("Hi1");
////                TireOrderDao tireOrderDao = new TireOrderDaoImpl();
////                boolean isOrderDetailSave = tireOrderDao.saveTireOrder(tireOrderDtos);
////
////                if (isOrderDetailSave){
////                    connection.commit();
////                    return true;
////                }
////            }
////            connection.rollback();
//            return false;
//        }catch (Exception e){
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select orderId from orders order by orderId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Orders orders) throws SQLException {
        return CrudUtil.execute("insert into orders values(?,?,?,?)",
                orders.getOrderId(),
                orders.getDate(),
                orders.getCustId(),
                orders.getEmpId()
            );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException {
        return false;
    }
}
