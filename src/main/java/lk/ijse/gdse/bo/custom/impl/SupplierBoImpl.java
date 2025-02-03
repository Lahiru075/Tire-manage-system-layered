package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.SupplierBo;
import lk.ijse.gdse.dao.costom.SupplierDao;
import lk.ijse.gdse.dao.costom.impl.SupplierDaoImpl;
import lk.ijse.gdse.dto.SupplierDto;
import lk.ijse.gdse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBoImpl implements SupplierBo {

    SupplierDao supplierDao = new SupplierDaoImpl();

    public String getNextId() throws SQLException {
        String lastId = supplierDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("S%03d", newIdIndex);
            return lastId;
        }

        return "S001";
    }

    public ArrayList<SupplierDto> getAll() throws SQLException {
//        return supplierDao.getAll();
        ArrayList<Supplier> suppliers = supplierDao.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers){
            supplierDtos.add(new SupplierDto(supplier.getSupId(), supplier.getName(), supplier.getEmail(), supplier.getContact(), supplier.getAddress()));
        }

        return supplierDtos;
    }

    public boolean save(SupplierDto supplierDto) throws SQLException {
//        return supplierDao.save(supplierDto);
        return supplierDao.save(new Supplier(supplierDto.getSupId(),supplierDto.getName(),supplierDto.getEmail(),supplierDto.getContact(),supplierDto.getAddress()));
    }

    public boolean delete(String supId) throws SQLException {
        return supplierDao.delete(supId);
    }

    public boolean update(SupplierDto supplierDto) throws SQLException {
        return supplierDao.update(new Supplier(supplierDto.getSupId(),supplierDto.getName(),supplierDto.getEmail(),supplierDto.getContact(),supplierDto.getAddress()));
    }

    public ArrayList<String> getAllSupplierId() throws SQLException {
        return supplierDao.getAllSupplierId();
    }
}
