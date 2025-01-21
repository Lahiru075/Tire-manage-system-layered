package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.SupplierDao;
import lk.ijse.gdse.dto.SupplierDto;
import lk.ijse.gdse.entity.Supplier;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select supId from supplier order by supId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier");

        ArrayList<Supplier> suppliers = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public boolean save(Supplier supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier values (?,?,?,?,?)",
                supplier.getSupId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact(),
                supplier.getAddress()
        );
    }

    public boolean delete(String supId) throws SQLException {
        return CrudUtil.execute("delete from supplier where supId=?", supId);
    }

    public boolean update(Supplier supplier) throws SQLException {
        return CrudUtil.execute("update supplier set name=?, email=?, coNo=?, address=? where supId=?",
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getSupId()
        );
    }

    public ArrayList<String> getAllSupplierId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier");

        ArrayList<String> supplierIds = new ArrayList<>();

        while (rst.next()) {
            supplierIds.add(rst.getString(1));
        }
        return supplierIds;
    }
}
