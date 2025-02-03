package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.SupplierOrderDao;
import lk.ijse.gdse.entity.SupplierOrder;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDaoImpl implements SupplierOrderDao {

//    StockDaoImpl stockDao = new StockDaoImpl();
//    PlaceOrderDaoImpl placeOrderDao = new PlaceOrderDaoImpl();


    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select supOrderId from supplier_order order by supOrderId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return "SO001";
    }

//    public boolean saveOrder(ArrayList<SupplierOrderDto> supplierOrderDtos) throws SQLException {
//        for (SupplierOrderDto supplierOrderDto : supplierOrderDtos){
//            boolean isOrderSave = save(supplierOrderDto);
//
//            if (!isOrderSave){
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean save(SupplierOrder supplierOrder) throws SQLException {
        return CrudUtil.execute("insert into supplier_order values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                supplierOrder.getOrderId(),
                supplierOrder.getSupId(),
                supplierOrder.getEmployeeId(),
                supplierOrder.getStockId(),
                supplierOrder.getTireModel(),
                supplierOrder.getQty(),
                supplierOrder.getOrderDate(),
                supplierOrder.getRequestDate(),
                supplierOrder.getTotal(),
                supplierOrder.getTireBrand(),
                supplierOrder.getYear(),
                supplierOrder.getOrderSize(),
                supplierOrder.getOrderStatus()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    public ArrayList<SupplierOrder> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier_order");

        ArrayList<SupplierOrder> supplierOrders = new ArrayList<>();

        while (rst.next()) {
            SupplierOrder supplierOrder = new SupplierOrder();

            supplierOrder.setOrderId(rst.getString(1));
            supplierOrder.setSupId(rst.getString(2));
            supplierOrder.setEmployeeId(rst.getString(3));
            supplierOrder.setStockId(rst.getString(4));
            supplierOrder.setTireModel(rst.getString(5));
            supplierOrder.setQty(rst.getInt(6));
            supplierOrder.setOrderDate(rst.getDate(7));
            supplierOrder.setRequestDate(rst.getDate(8));
            supplierOrder.setTotal(rst.getDouble(9));
            supplierOrder.setTireBrand(rst.getString(10));
            supplierOrder.setYear(rst.getInt(11));
            supplierOrder.setOrderSize(rst.getString(12));
            supplierOrder.setOrderStatus(rst.getString(13));

            supplierOrders.add(supplierOrder);
        }
        return supplierOrders;
    }

    public boolean update(SupplierOrder supplierOrder) throws SQLException {
        return CrudUtil.execute("UPDATE supplier_order SET supId=?, empId=? , tireModel=?, qty=?, order_date=?, request_date=?, total_amount=?, tire_brand=?, year=?, size=?, order_status=? WHERE supOrderId=? AND stockId=?",
                supplierOrder.getSupId(),
                supplierOrder.getEmployeeId(),
                supplierOrder.getTireModel(),
                supplierOrder.getQty(),
                supplierOrder.getOrderDate(),
                supplierOrder.getRequestDate(),
                supplierOrder.getTotal(),
                supplierOrder.getTireBrand(),
                supplierOrder.getYear(),
                supplierOrder.getOrderSize(),
                supplierOrder.getOrderStatus(),
                supplierOrder.getOrderId(),
                supplierOrder.getStockId()
        );

    }

    public boolean deleteSupplierOrder(String orderId, String stockId) throws SQLException {
        return CrudUtil.execute("delete from supplier_order where supOrderId = ? and stockId = ?", orderId, stockId);
    }

//    public boolean addSupplierOrder(SupplierOrder supplierOrder) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//
//            SupplierOrderBo supplierOrderBo = new SupplierOrderBoImpl();
//
//            return supplierOrderBo.addSupplierOrder(new SupplierOrderDto(
//                    supplierOrder.getOrderId(),
//                    supplierOrder.getSupId(),
//                    supplierOrder.getEmployeeId(),
//                    supplierOrder.getStockId(),
//                    supplierOrder.getOrderDate(),
//                    supplierOrder.getYear(),
//                    supplierOrder.getRequestDate(),
//                    supplierOrder.getTireModel(),
//                    supplierOrder.getTireBrand(),
//                    supplierOrder.getOrderStatus(),
//                    supplierOrder.getOrderSize(),
//                    supplierOrder.getTotal(),
//                    supplierOrder.getQty()));
//
//            connection.setAutoCommit(false);
//            boolean isUpdate = CrudUtil.execute("UPDATE supplier_order SET order_status = ? WHERE supOrderId=? AND stockId=?",
//                    "Completed",
//                    supplierOrder.getOrderId(),
//                    supplierOrder.getStockId()
//            );
//
//            if (isUpdate) {
//                String description = supplierOrder.getTireBrand() + " " + supplierOrder.getTireModel() + " " + supplierOrder.getOrderSize();
//
//                StockDao stockDao = new StockDaoImpl();
//                boolean isTireIsExists = stockDao.checkIsExists(description);
//
//                if (isTireIsExists) {
//                    String getStockId = stockDao.getStockId(description);
//
//                    String date = LocalDate.now().toString();
//
//                    boolean isStockUpdate = stockDao.updateStock(getStockId, supplierOrder.getQty(), date);
//
//                    if (isStockUpdate) {
//                        connection.commit();
//                        return true;
//                    }
//                }
//
//                if (!isTireIsExists) {
//                    String brand = supplierOrder.getTireBrand();
//                    String model = supplierOrder.getTireModel();
//                    String size = supplierOrder.getOrderSize();
//
//                    String stockId = stockBo.getNextId();
//                    PlaceOrderDao placeOrderDao = new PlaceOrderDaoImpl();
//                    String tireId = placeOrderDao.checkIsExists(brand, model, size);
//
//                    if (tireId != null) {
//                        StockDto stockDto = new StockDto();
//                        stockDto.setStockId(stockId);
//                        stockDto.setDescription(description);
//                        stockDto.setLast_update(LocalDate.now().toString());
//                        stockDto.setRecode_level(50);
//                        stockDto.setQty(supplierOrder.getQty());
//                        stockDto.setTireId(tireId);
//
//                        boolean isStockSave = stockBo.save(stockDto);
//
//                        if (isStockSave) {
//                            connection.commit();
//                            return true;
//                        }
//                    } else {
//                        new Alert(Alert.AlertType.ERROR, "Please add tire your tire table..").showAndWait();
//                    }
//                }
//            }
//
//            connection.rollback();
//        } catch (SQLException e) {
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }

    @Override
    public boolean isUpdate(String completed, String orderId, String stockId) throws SQLException {
        boolean isUpdate = CrudUtil.execute("UPDATE supplier_order SET order_status = ? WHERE supOrderId=? AND stockId=?",
                completed,
                orderId,
                stockId
        );

        return isUpdate;
    }
}