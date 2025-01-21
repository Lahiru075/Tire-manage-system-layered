package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.StockBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.StockDao;
import lk.ijse.gdse.dao.costom.impl.StockDaoImpl;
import lk.ijse.gdse.dto.StockDto;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.Stock;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockBoImpl implements StockBo {

    StockDao stockDao = (StockDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STOCK);

    public ArrayList<StockDto> getAll() throws SQLException {
//        return stockDao.getAll();
        ArrayList<Stock> stocks = stockDao.getAll();
        ArrayList<StockDto> stockDtos = new ArrayList<>();

        for (Stock stock : stocks){
            stockDtos.add(new StockDto(
                    stock.getStockId(),
                    stock.getDescription(),
                    stock.getLast_update(),
                    stock.getRecode_level(),
                    stock.getQty(),
                    stock.getTireId()
            ));
        }

        return stockDtos;
    }

    public boolean save(StockDto stockDto) throws SQLException {
//        return stockDao.save(stockDto);
        return stockDao.save(new Stock(
                stockDto.getStockId(),
                stockDto.getDescription(),
                stockDto.getLast_update(),
                stockDto.getRecode_level(),
                stockDto.getQty(),
                stockDto.getTireId()
        ));
    }

    public boolean update(StockDto stockDto) throws SQLException {
//        return stockDao.update(stockDto);
        return stockDao.update(new Stock(
                stockDto.getStockId(),
                stockDto.getDescription(),
                stockDto.getLast_update(),
                stockDto.getRecode_level(),
                stockDto.getQty(),
                stockDto.getTireId()
        ));
    }

    public boolean delete(String text) throws SQLException {
        return stockDao.delete(text);
    }

    public String getNextId() throws SQLException {
        return stockDao.getNextId();
    }

    public boolean reduceQty(TireOrderDto tireOrderDto) throws SQLException {
        return stockDao.reduceQty(tireOrderDto);
    }

    public ArrayList<String> getAllStockId() throws SQLException {
        return stockDao.getAllStockId();
    }

    public boolean checkIsExists(String description) throws SQLException {
        return stockDao.checkIsExists(description);
    }

    public String getStockId(String description) throws SQLException {
        return stockDao.getStockId(description);
    }

    public boolean updateStock(String getStockId, int qty, String date) throws SQLException {
        return stockDao.updateStock(getStockId,qty,date);
    }

    public String getRecodLevel(String tireId) throws SQLException {
        return stockDao.getRecodLevel(tireId);
    }

    public int getQty(String description) throws SQLException {
        return stockDao.getQty(description);
    }
}
