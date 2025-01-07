package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.StockDto;
import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao {
    ArrayList<StockDto> getAllStock() throws SQLException;
    boolean isSaved(StockDto stockDto) throws SQLException;
    boolean isUpdate(StockDto stockDto) throws SQLException;
    boolean deleteStock(String text) throws SQLException;
    String getNextStockId() throws SQLException;
    boolean reduceQty(TireOrderDto tireOrderDto) throws SQLException;
    ArrayList<String> getAllStockId() throws SQLException;
    boolean checkIsExists(String description) throws SQLException;
    String getStockId(String description) throws SQLException;
    boolean updateStock(String getStockId, int qty, String date) throws SQLException;
    String getRecodLevel(String tireId) throws SQLException;
    int getQty(String description) throws SQLException;
}
