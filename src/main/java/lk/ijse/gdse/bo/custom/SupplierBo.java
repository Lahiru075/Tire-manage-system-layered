package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBo;
import lk.ijse.gdse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBo {
    String getNextId() throws SQLException;
    ArrayList<SupplierDto> getAll() throws SQLException;
    boolean save(SupplierDto supplierDto) throws SQLException;
    boolean delete(String supId) throws SQLException;
    boolean update(SupplierDto supplierDto) throws SQLException;
    ArrayList<String> getAllSupplierId() throws SQLException;
}
