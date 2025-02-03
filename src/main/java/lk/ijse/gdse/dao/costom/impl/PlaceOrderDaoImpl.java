package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.PlaceOrderDao;
import lk.ijse.gdse.entity.PlaceOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDaoImpl implements PlaceOrderDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select tireId from tire order by tireId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public ArrayList<PlaceOrder> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from tire");

        ArrayList<PlaceOrder> placeOrders = new ArrayList<>();

        while (rst.next()){
            PlaceOrder placeOrder = new PlaceOrder();

            placeOrder.setTireId(rst.getString(1));
            placeOrder.setTireBrand(rst.getString(2));
            placeOrder.setTireModel(rst.getString(3));
            placeOrder.setTireSize(rst.getString(4));
            placeOrder.setYear(Integer.parseInt(rst.getString(5)));
            placeOrder.setTirePrice(Double.parseDouble(rst.getString(6)));


            placeOrders.add(placeOrder);
        }
        return placeOrders;
    }

    public int getQty(String tireId) throws SQLException {
        ResultSet rst1 = CrudUtil.execute("select * from stock where tireId = ?",tireId);

        int qty = 0;
        if(rst1.next()){
            qty = rst1.getInt(5);
        }
        return qty;
    }

    public PlaceOrder getTire(String tireId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from tire where tireId = ?",tireId);

        PlaceOrder placeOrder = new PlaceOrder();

        if(rst.next()){
            placeOrder.setTireId(rst.getString(1));
            placeOrder.setTireBrand(rst.getString(2));
            placeOrder.setTireModel(rst.getString(3));
            placeOrder.setTireSize(rst.getString(4));
            placeOrder.setYear(Integer.parseInt(rst.getString(5)));
            placeOrder.setTirePrice(Double.parseDouble(rst.getString(6)));
        }
        return placeOrder;
    }

    public ArrayList<PlaceOrder> getTireByBrand(String brand) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM tire WHERE brand LIKE ?", "%" + brand + "%");

        ArrayList<PlaceOrder> placeOrders = new ArrayList<>();

        while (rst.next()){
            PlaceOrder placeOrder = new PlaceOrder();

            placeOrder.setTireId(rst.getString(1));
            placeOrder.setTireBrand(rst.getString(2));
            placeOrder.setTireModel(rst.getString(3));
            placeOrder.setTireSize(rst.getString(4));
            placeOrder.setYear(Integer.parseInt(rst.getString(5)));
            placeOrder.setTirePrice(Double.parseDouble(rst.getString(6)));

            placeOrders.add(placeOrder);
        }
        return placeOrders;
    }

    public String checkIsExists(String brand, String model, String size) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from tire where brand = ? and model = ? and size = ?", brand, model, size);

        String result = null;

        if (rst.next()) {
            result = rst.getString(1);
            System.out.println(rst.getString(1));
        }
        System.out.println(result);
        return result;

    }

    public boolean save(PlaceOrder placeOrder) throws SQLException {
        return CrudUtil.execute("INSERT INTO tire VALUES (?,?,?,?,?,?)",
                placeOrder.getTireId(),
                placeOrder.getTireBrand(),
                placeOrder.getTireModel(),
                placeOrder.getTireSize(),
                placeOrder.getYear(),
                placeOrder.getTirePrice()
        );
    }

    public boolean delete(String tireId) throws SQLException {
        return CrudUtil.execute("delete from tire where tireId = ?", tireId);
    }

    public boolean update(PlaceOrder placeOrder) throws SQLException {
        return CrudUtil.execute("update tire set brand = ?, model = ?, size = ?, year = ?, price = ? where tireId = ?",
                placeOrder.getTireBrand(),
                placeOrder.getTireModel(),
                placeOrder.getTireSize(),
                placeOrder.getYear(),
                placeOrder.getTirePrice(),
                placeOrder.getTireId()
        );
    }

    public ArrayList<String> getAllTireId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from tire");

        ArrayList<String> tireIds = new ArrayList<>();

        while(rst.next()){
            tireIds.add(rst.getString(1));
        }

        return tireIds;

    }
}
