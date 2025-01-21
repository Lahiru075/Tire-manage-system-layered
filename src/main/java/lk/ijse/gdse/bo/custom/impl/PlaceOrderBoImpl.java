package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PlaceOrderBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.PlaceOrderDao;
import lk.ijse.gdse.dao.costom.impl.PlaceOrderDaoImpl;
import lk.ijse.gdse.dto.PlaceOrderDto;
import lk.ijse.gdse.entity.PlaceOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    PlaceOrderDao placeOrderDao = (PlaceOrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PLACE_ORDER);

    public String getNextId() throws SQLException {
        return placeOrderDao.getNextId();
    }

    public ArrayList<PlaceOrderDto> getAll() throws SQLException {
//        return placeOrderDao.getAll();
        ArrayList<PlaceOrder> placeOrders = placeOrderDao.getAll();
        ArrayList<PlaceOrderDto> placeOrderDtos = new ArrayList<>();

        for (PlaceOrder placeOrder : placeOrders){
            placeOrderDtos.add(new PlaceOrderDto(
                    placeOrder.getTireId(),
                    placeOrder.getTireBrand(),
                    placeOrder.getTireModel(),
                    placeOrder.getTireSize(),
                    placeOrder.getYear(),
                    placeOrder.getTirePrice()
            ));
        }

        return placeOrderDtos;
    }

    public int getQty(String tireId) throws SQLException {
        return placeOrderDao.getQty(tireId);
    }

    public PlaceOrderDto getTire(String tireId) throws SQLException {
//        return placeOrderDao.getTire(tireId);
        PlaceOrder placeOrder = placeOrderDao.getTire(tireId);

        return new PlaceOrderDto(
                placeOrder.getTireId(),
                placeOrder.getTireBrand(),
                placeOrder.getTireModel(),
                placeOrder.getTireSize(),
                placeOrder.getYear(),
                placeOrder.getTirePrice()
        );
    }

    public ArrayList<PlaceOrderDto> getTireByBrand(String brand) throws SQLException {
//        return placeOrderDao.getTireByBrand(brand);
        ArrayList<PlaceOrder> placeOrders = placeOrderDao.getTireByBrand(brand);
        ArrayList<PlaceOrderDto> placeOrderDtos = new ArrayList<>();

        for (PlaceOrder placeOrder : placeOrders){
            placeOrderDtos.add(new PlaceOrderDto(
                    placeOrder.getTireId(),
                    placeOrder.getTireBrand(),
                    placeOrder.getTireModel(),
                    placeOrder.getTireSize(),
                    placeOrder.getYear(),
                    placeOrder.getTirePrice()
            ));
        }

        return placeOrderDtos;
    }

    public String checkIsExists(String brand, String model, String size) throws SQLException {
        return placeOrderDao.checkIsExists(brand,model,size);
    }

    public boolean save(PlaceOrderDto placeOrderDto) throws SQLException {
//        return placeOrderDao.save(placeOrderDto);
        return placeOrderDao.save(new PlaceOrder(
                placeOrderDto.getTireId(),
                placeOrderDto.getTireBrand(),
                placeOrderDto.getTireModel(),
                placeOrderDto.getTireSize(),
                placeOrderDto.getYear(),
                placeOrderDto.getTirePrice()
        ));
    }

    public boolean delete(String tireId) throws SQLException {
        return placeOrderDao.delete(tireId);
    }

    public boolean update(PlaceOrderDto placeOrderDto) throws SQLException {
        return placeOrderDao.update(new PlaceOrder(
                placeOrderDto.getTireId(),
                placeOrderDto.getTireBrand(),
                placeOrderDto.getTireModel(),
                placeOrderDto.getTireSize(),
                placeOrderDto.getYear(),
                placeOrderDto.getTirePrice()
        ));
    }

    public ArrayList<String> getAllTireId() throws SQLException {
        return placeOrderDao.getAllTireId();
    }
}
