package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.PlaceOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBo extends SuperBo{
    String getNextId() throws SQLException;
    ArrayList<PlaceOrderDto> getAll() throws SQLException;
    int getQty(String tireId) throws SQLException;
    PlaceOrderDto getTire(String tireId) throws SQLException;
    ArrayList<PlaceOrderDto> getTireByBrand(String brand) throws SQLException;
    String checkIsExists(String brand, String model, String size) throws SQLException;
    boolean save(PlaceOrderDto placeOrderDto) throws SQLException;
    boolean delete(String tireId) throws SQLException;
    boolean update(PlaceOrderDto placeOrderDto) throws SQLException;
    ArrayList<String> getAllTireId() throws SQLException;
}
