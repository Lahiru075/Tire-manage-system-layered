package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.StockDto;
import lk.ijse.gdse.dto.TireOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBo extends SuperBo{
    ArrayList<StockDto> getAll() throws SQLException;
    boolean save(StockDto stockDto) throws SQLException;
    boolean update(StockDto stockDto) throws SQLException;
    boolean delete(String text) throws SQLException;
    String getNextId() throws SQLException;
    boolean reduceQty(TireOrderDto tireOrderDto) throws SQLException;
    ArrayList<String> getAllStockId() throws SQLException;
    boolean checkIsExists(String description) throws SQLException;
    String getStockId(String description) throws SQLException;
    boolean updateStock(String getStockId, int qty, String date) throws SQLException;
    String getRecodLevel(String tireId) throws SQLException;
    int getQty(String description) throws SQLException;
}
