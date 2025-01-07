package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.SupplierOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDao {
    String getNextOrderId() throws SQLException;
    boolean saveOrder(ArrayList<SupplierOrderDto> supplierOrderDtos) throws SQLException;
    boolean saveSupplierOrder(SupplierOrderDto supplierOrderDto) throws SQLException;
    ArrayList<SupplierOrderDto> getAllSupplierOrders() throws SQLException;
    boolean isUpdate(SupplierOrderDto supplierOrderDto) throws SQLException;
    boolean deleteSupplierOrder(String orderId, String stockId) throws SQLException;
    boolean addSupplierOrder(SupplierOrderDto supplierOrderDto) throws SQLException;

}
