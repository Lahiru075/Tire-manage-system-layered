package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.PlaceOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderDao {
    String getNextTireId() throws SQLException;
    ArrayList<PlaceOrderDto> getAllTires() throws SQLException;
    int getQty(String tireId) throws SQLException;
    PlaceOrderDto getTire(String tireId) throws SQLException;
    ArrayList<PlaceOrderDto> getTireByBrand(String brand) throws SQLException;
    String checkIsExists(String brand, String model, String size) throws SQLException;
    boolean saveTire(PlaceOrderDto placeOrderDto) throws SQLException;
    boolean deleteTire(String tireId) throws SQLException;
    boolean updateTire(PlaceOrderDto placeOrderDto) throws SQLException;
    ArrayList<String> getAllTireId() throws SQLException;
}
