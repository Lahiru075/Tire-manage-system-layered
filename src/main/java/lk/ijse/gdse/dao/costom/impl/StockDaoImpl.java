package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.StockDao;
import lk.ijse.gdse.dto.TireOrderDto;
import lk.ijse.gdse.entity.Stock;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDaoImpl implements StockDao {

    public ArrayList<Stock> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from stock");

        ArrayList<Stock> stocks = new ArrayList<>();

        while (rst.next()) {
            Stock stock = new Stock(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
            stocks.add(stock);
        }
        return stocks;
    }

    public boolean save(Stock stock) throws SQLException {
        return CrudUtil.execute("insert into stock values(?,?,?,?,?,?)",
                stock.getStockId(),
                stock.getDescription(),
                stock.getLast_update(),
                stock.getRecode_level(),
                stock.getQty(),
                stock.getTireId()
        );
    }

    public boolean update(Stock stock) throws SQLException {
        return CrudUtil.execute("update stock set description = ?, last_update = ?, recode_level = ?, qty = ?, tireId = ? where stockId = ?",
                stock.getDescription(),
                stock.getLast_update(),
                stock.getRecode_level(),
                stock.getQty(),
                stock.getTireId(),
                stock.getStockId()
        );
    }

    public boolean delete(String text) throws SQLException {
        return CrudUtil.execute("delete from stock where stockId = ?", text);
    }

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select stockId from stock order by stockId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public boolean reduceQty(TireOrderDto tireOrderDto) throws SQLException {
        return CrudUtil.execute("update stock set qty = qty - ? where tireId = ?",
                tireOrderDto.getQty(),
                tireOrderDto.getTireId()
        );

    }

    public ArrayList<String> getAllStockId() throws SQLException {
        ArrayList<String> stockId = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("select * from stock");
        while (rst.next()) {
            stockId.add(rst.getString(1));
        }
        return stockId;
    }

    public boolean checkIsExists(String description) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from stock where description = ?", description);

        String desc = null;

        if (rst.next()){
            desc = rst.getString(2);
        }

        if (desc != null) {
            return true;
        }
        return false;
    }

    public String getStockId(String description) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from stock where description = ?", description);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public boolean updateStock(String getStockId, int qty, String date) throws SQLException {
        return CrudUtil.execute("update stock set qty = qty + ? , last_update = ? where stockId = ?", qty, date ,getStockId);
    }

    public String getRecodLevel(String tireId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from stock where tireId = ?", tireId);
        if (rst.next()) {
            return rst.getString(4);
        }
        return null;
    }

    public int getQty(String description) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from stock where description = ?", description);

        if (rst.next()) {
            return rst.getInt(5);
        }
        return 0;
    }
}
