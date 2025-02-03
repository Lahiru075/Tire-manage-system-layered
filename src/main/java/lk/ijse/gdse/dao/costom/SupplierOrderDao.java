package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.entity.SupplierOrder;

import java.sql.SQLException;

public interface SupplierOrderDao extends CrudDao<SupplierOrder> {
    boolean deleteSupplierOrder(String orderId, String stockId) throws SQLException;
    boolean isUpdate(String completed, String orderId, String stockId) throws SQLException;
//    boolean addSupplierOrder(SupplierOrder supplierOrder) throws SQLException;
}
