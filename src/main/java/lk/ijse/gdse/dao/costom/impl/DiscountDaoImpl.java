package lk.ijse.gdse.dao.costom.impl;


import lk.ijse.gdse.dao.costom.DiscountDao;
import lk.ijse.gdse.dto.DiscountDto;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscountDaoImpl implements DiscountDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select descId from discount order by descId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public ArrayList<DiscountDto> getAll() throws SQLException {
        return null;
    }

    public boolean save(DiscountDto discountDto) throws SQLException {
        return CrudUtil.execute("insert into discount values (?,?,?,?)",
                discountDto.getDiscId(),
                discountDto.getAmount(),
                discountDto.getDate(),
                discountDto.getPaymentId()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(DiscountDto dto) throws SQLException {
        return false;
    }
}
