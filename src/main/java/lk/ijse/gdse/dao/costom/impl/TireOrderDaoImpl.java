package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.bo.custom.StockBo;
import lk.ijse.gdse.dao.costom.StockDao;
import lk.ijse.gdse.dao.costom.TireOrderDao;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.TireOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class TireOrderDaoImpl implements TireOrderDao {

//    private final StockDaoImpl stockDao = new StockDaoImpl();


//    public boolean saveTireOrder(ArrayList<TireOrderDto> tireOrderDtos) throws SQLException {
//
//        for (TireOrderDto tireOrderDto : tireOrderDtos) {
//
//            boolean isTireOrderSave = saveTireOrderDetails(tireOrderDto);
//
//            if (!isTireOrderSave) {
//                return false;
//            }
//
//            StockDaoImpl stockDao = new StockDaoImpl();
//            boolean isStockUpdate = stockDao.reduceQty(tireOrderDto);
//
//            if (!isStockUpdate) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<TireOrder> getAll() throws SQLException {
        return null;
    }

    public boolean save(TireOrder tireOrder) throws SQLException {
        return CrudUtil.execute("insert into tire_order values (?,?,?,?,?,?)",
                tireOrder.getOrderId(),
                tireOrder.getTireId(),
                tireOrder.getDescription(),
                tireOrder.getPayment_method(),
                tireOrder.getQty(),
                tireOrder.getTotal_amount()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(TireOrder entity) throws SQLException {
        return false;
    }
}
