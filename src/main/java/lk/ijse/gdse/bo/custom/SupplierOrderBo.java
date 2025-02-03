package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.SupplierOrderDto;

import java.sql.SQLException;
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
