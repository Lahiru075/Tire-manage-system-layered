package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDao {
    String getNextSupplierId() throws SQLException;
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException;
    boolean seveSupplier(SupplierDto supplierDto) throws SQLException;
    boolean deleteSupplier(String supId) throws SQLException;
    boolean updateSupplier(SupplierDto supplierDto) throws SQLException;
    ArrayList<String> getAllSupplierId() throws SQLException;
}
