package lk.ijse.gdse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.StockBo;
import lk.ijse.gdse.bo.custom.SupplierOrderBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.PlaceOrderDao;
import lk.ijse.gdse.dao.costom.SupplierOrderDao;
import lk.ijse.gdse.dao.costom.impl.SupplierOrderDaoImpl;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.StockDto;
import lk.ijse.gdse.dto.SupplierOrderDto;
import lk.ijse.gdse.entity.SupplierOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplierOrderBoImpl implements SupplierOrderBo {

    SupplierOrderDao supplierOrderDao = new SupplierOrderDaoImpl();
    StockBo stockBo = (StockBo) BOFactory.getInstance().getBO(BOFactory.BOType.STOCK);
    PlaceOrderDao placeOrderDao = (PlaceOrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PLACE_ORDER);

    public String getNextId() throws SQLException {
        String lastId = supplierOrderDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("SO%03d", newIdIndex);
            return lastId;
        }

        return "SO001";
    }

    public boolean saveOrder(ArrayList<SupplierOrderDto> supplierOrderDtos) throws SQLException {
        for (SupplierOrderDto supplierOrderDto : supplierOrderDtos){
            boolean isOrderSave = save(supplierOrderDto);

            if (!isOrderSave){
                return false;
            }
        }
        return true;
    }

    public boolean save(SupplierOrderDto supplierOrderDto) throws SQLException {

        return supplierOrderDao.save(new SupplierOrder(
                supplierOrderDto.getOrderId(),
                supplierOrderDto.getStockId(),
                supplierOrderDto.getSupId(),
                supplierOrderDto.getEmployeeId(),
                supplierOrderDto.getOrderDate(),
                supplierOrderDto.getYear(),
                supplierOrderDto.getRequestDate(),
                supplierOrderDto.getTireModel(),
                supplierOrderDto.getTireBrand(),
                supplierOrderDto.getOrderStatus(),
                supplierOrderDto.getOrderSize(),
                supplierOrderDto.getTotal(),
                supplierOrderDto.getQty()
        ));

    }

    public boolean delete(String Id) throws SQLException {
        return false;
    }

    public ArrayList<SupplierOrderDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier_order");

        ArrayList<SupplierOrderDto> supplierOrderDtos = new ArrayList<>();

        while (rst.next()){
            SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

            supplierOrderDto.setOrderId(rst.getString(1));
            supplierOrderDto.setSupId(rst.getString(2));
            supplierOrderDto.setEmployeeId(rst.getString(3));
            supplierOrderDto.setStockId(rst.getString(4));
            supplierOrderDto.setTireModel(rst.getString(5));
            supplierOrderDto.setQty(rst.getInt(6));
            supplierOrderDto.setOrderDate(rst.getDate(7));
            supplierOrderDto.setRequestDate(rst.getDate(8));
            supplierOrderDto.setTotal(rst.getDouble(9));
            supplierOrderDto.setTireBrand(rst.getString(10));
            supplierOrderDto.setYear(rst.getInt(11));
            supplierOrderDto.setOrderSize(rst.getString(12));
            supplierOrderDto.setOrderStatus(rst.getString(13));

            supplierOrderDtos.add(supplierOrderDto);
        }
        return supplierOrderDtos;
    }

    public boolean update(SupplierOrderDto supplierOrderDto) throws SQLException {
//        return CrudUtil.execute("UPDATE supplier_order SET supId=?, empId=? , tireModel=?, qty=?, order_date=?, request_date=?, total_amount=?, tire_brand=?, year=?, size=?, order_status=? WHERE supOrderId=? AND stockId=?",
//                supplierOrderDto.getSupId(),
//                supplierOrderDto.getEmployeeId(),
//                supplierOrderDto.getTireModel(),
//                supplierOrderDto.getQty(),
//                supplierOrderDto.getOrderDate(),
//                supplierOrderDto.getRequestDate(),
//                supplierOrderDto.getTotal(),
//                supplierOrderDto.getTireBrand(),
//                supplierOrderDto.getYear(),
//                supplierOrderDto.getOrderSize(),
//                supplierOrderDto.getOrderStatus(),
//                supplierOrderDto.getOrderId(),
//                supplierOrderDto.getStockId()
//        );

        return supplierOrderDao.update(new SupplierOrder(
                supplierOrderDto.getOrderId(),
                supplierOrderDto.getStockId(),
                supplierOrderDto.getSupId(),
                supplierOrderDto.getEmployeeId(),
                supplierOrderDto.getOrderDate(),
                supplierOrderDto.getYear(),
                supplierOrderDto.getRequestDate(),
                supplierOrderDto.getTireModel(),
                supplierOrderDto.getTireBrand(),
                supplierOrderDto.getOrderStatus(),
                supplierOrderDto.getOrderSize(),
                supplierOrderDto.getTotal(),
                supplierOrderDto.getQty()

        ));
    }

    public boolean deleteSupplierOrder(String orderId, String stockId) throws SQLException {
//        return CrudUtil.execute("delete from supplier_order where supOrderId = ? and stockId = ?", orderId, stockId);
        return supplierOrderDao.deleteSupplierOrder(orderId,stockId);
    }

    public boolean addSupplierOrder(SupplierOrderDto supplierOrderDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
//            boolean isUpdate = CrudUtil.execute("UPDATE supplier_order SET order_status = ? WHERE supOrderId=? AND stockId=?",
//                    "Completed",
//                    supplierOrderDto.getOrderId(),
//                    supplierOrderDto.getStockId()
//            );

            boolean isUpdate = supplierOrderDao.isUpdate("completed",supplierOrderDto.getOrderId(),supplierOrderDto.getStockId());

            if (isUpdate){
                String description = supplierOrderDto.getTireBrand() + " " + supplierOrderDto.getTireModel() + " " + supplierOrderDto.getOrderSize();

                boolean isTireIsExists = stockBo.checkIsExists(description);

                if (isTireIsExists) {
                    String getStockId = stockBo.getStockId(description);

                    String date = LocalDate.now().toString();

                    boolean isStockUpdate = stockBo.updateStock(getStockId, supplierOrderDto.getQty(), date);

                    if (isStockUpdate){
                        connection.commit();
                        return true;
                    }
                }

                if (!isTireIsExists) {
                    String brand = supplierOrderDto.getTireBrand();
                    String model = supplierOrderDto.getTireModel();
                    String size = supplierOrderDto.getOrderSize();

                    String stockId = stockBo.getNextId();
                    String tireId = placeOrderDao.checkIsExists(brand, model, size);

                    if (tireId != null) {
                        StockDto stockDto = new StockDto();
                        stockDto.setStockId(stockId);
                        stockDto.setDescription(description);
                        stockDto.setLast_update(LocalDate.now().toString());
                        stockDto.setRecode_level(50);
                        stockDto.setQty(supplierOrderDto.getQty());
                        stockDto.setTireId(tireId);

                        boolean isStockSave = stockBo.save(stockDto);

                        if (isStockSave){
                            connection.commit();
                            return true;
                        }

                    }else{
                        new Alert(Alert.AlertType.ERROR, "Please add tire your tire table..").showAndWait();
                    }
                }
            }

            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
