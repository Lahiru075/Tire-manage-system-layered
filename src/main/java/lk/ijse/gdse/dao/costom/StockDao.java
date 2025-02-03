package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao extends CrudDao<Stock> {
    boolean reduceQty(TireOrderDto tireOrderDto) throws SQLException;
    ArrayList<String> getAllStockId() throws SQLException;
    boolean checkIsExists(String description) throws SQLException;
    String getStockId(String description) throws SQLException;
    boolean updateStock(String getStockId, int qty, String date) throws SQLException;
    String getRecodLevel(String tireId) throws SQLException;
    int getQty(String description) throws SQLException;
}
