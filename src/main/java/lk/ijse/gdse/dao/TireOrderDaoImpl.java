package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class TireOrderDaoImpl implements TireOrderDao {

//    private final StockDaoImpl stockDao = new StockDaoImpl();

    public boolean saveTireOrder(ArrayList<TireOrderDto> tireOrderDtos) throws SQLException {

        for (TireOrderDto tireOrderDto : tireOrderDtos) {

            boolean isTireOrderSave = saveTireOrderDetails(tireOrderDto);

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

    public boolean saveTireOrderDetails(TireOrderDto tireOrderDto) throws SQLException {
        return CrudUtil.execute("insert into tire_order values (?,?,?,?,?,?)",
                tireOrderDto.getOrderId(),
                tireOrderDto.getTireId(),
                tireOrderDto.getDescription(),
                tireOrderDto.getPayment_method(),
                tireOrderDto.getQty(),
                tireOrderDto.getTotal_amount()
        );
    }
}
