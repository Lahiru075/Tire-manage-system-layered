package lk.ijse.gdse.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.gdse.dao.costom.PlaceOrderDao;
import lk.ijse.gdse.dao.costom.impl.PlaceOrderDaoImpl;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.StockDto;
import lk.ijse.gdse.dto.SupplierOrderDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface SupplierOrderBo extends SuperBo {
    String getNextId() throws SQLException;
    boolean save(SupplierOrderDto supplierOrderDto) throws SQLException;
    boolean delete(String Id) throws SQLException;
    ArrayList<SupplierOrderDto> getAll() throws SQLException;
    boolean update(SupplierOrderDto supplierOrderDto) throws SQLException;
    boolean deleteSupplierOrder(String orderId, String stockId) throws SQLException;
    boolean addSupplierOrder(SupplierOrderDto supplierOrderDto) throws SQLException;
    boolean saveOrder(ArrayList<SupplierOrderDto> supplierOrderDtos) throws SQLException;
}
