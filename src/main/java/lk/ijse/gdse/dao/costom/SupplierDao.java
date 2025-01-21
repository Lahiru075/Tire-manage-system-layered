package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.SupplierDto;
import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDao extends CrudDao<Supplier> {
    ArrayList<String> getAllSupplierId() throws SQLException;
}
