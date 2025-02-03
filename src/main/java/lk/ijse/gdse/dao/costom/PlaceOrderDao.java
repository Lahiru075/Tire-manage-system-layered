package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.entity.PlaceOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderDao extends CrudDao<PlaceOrder> {
    int getQty(String tireId) throws SQLException;
    PlaceOrder getTire(String tireId) throws SQLException;
    ArrayList<PlaceOrder> getTireByBrand(String brand) throws SQLException;
    String checkIsExists(String brand, String model, String size) throws SQLException;
    ArrayList<String> getAllTireId() throws SQLException;
}
