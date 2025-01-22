package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TireOrderBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.TireOrderDao;
import lk.ijse.gdse.dao.costom.impl.StockDaoImpl;
import lk.ijse.gdse.dao.costom.impl.TireOrderDaoImpl;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.TireOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class TireOrderBoImpl implements TireOrderBo {

    TireOrderDao tireOrderDao = (TireOrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TIRE_ORDER);

    public boolean saveTireOrder(ArrayList<TireOrderDto> tireOrderDtos) throws SQLException {

        for (TireOrderDto tireOrderDto : tireOrderDtos) {

            boolean isTireOrderSave = save(tireOrderDto);

            if (!isTireOrderSave) {
                return false;
            }

            StockDaoImpl stockDao = new StockDaoImpl();
            boolean isStockUpdate = stockDao.reduceQty(tireOrderDto);

            if (!isStockUpdate) {
                return false;
            }
        }

        return true;
    }

    public boolean save(TireOrderDto tireOrderDto) throws SQLException {
        return tireOrderDao.save(new TireOrder(
                tireOrderDto.getOrderId(),
                tireOrderDto.getTireId(),
                tireOrderDto.getDescription(),
                tireOrderDto.getPayment_method(),
                tireOrderDto.getQty(),
                tireOrderDto.getTotal_amount()
        ));
    }
}
