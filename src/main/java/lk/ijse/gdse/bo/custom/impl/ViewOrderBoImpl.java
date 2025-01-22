package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ViewOrderBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.QueryDao;
import lk.ijse.gdse.dao.costom.impl.QueryDaoImpl;
import lk.ijse.gdse.dto.OrderViewDto;
import lk.ijse.gdse.entity.OrderView;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrderBoImpl implements ViewOrderBo {

    QueryDao queryDao = (QueryDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    public ArrayList<OrderViewDto> getAllOrders() throws SQLException {
        ArrayList<OrderView> orderViews = queryDao.getAllOrders();
        ArrayList<OrderViewDto> orderViewDtos = new ArrayList<>();

        for (OrderView orderView : orderViews){
            orderViewDtos.add(new OrderViewDto(
                    orderView.getOrderId(),
                    orderView.getDate(),
                    orderView.getCustId(),
                    orderView.getEmpId(),
                    orderView.getTireId(),
                    orderView.getDescription(),
                    orderView.getPayment_method(),
                    orderView.getQty(),
                    orderView.getTotal_amount()
            ));
        }
        return orderViewDtos;
    }

    public ArrayList<OrderViewDto> searchByCustId(String custId) throws SQLException {
        ArrayList<OrderView> orderViews = queryDao.searchByCustId(custId);

        ArrayList<OrderViewDto> orderViewDtos = new ArrayList<>();

        for (OrderView orderView : orderViews){
            orderViewDtos.add(new OrderViewDto(
                    orderView.getOrderId(),
                    orderView.getDate(),
                    orderView.getCustId(),
                    orderView.getEmpId(),
                    orderView.getTireId(),
                    orderView.getDescription(),
                    orderView.getPayment_method(),
                    orderView.getQty(),
                    orderView.getTotal_amount()
            ));
        }
        return orderViewDtos;
    }

    public ArrayList<OrderViewDto> searchByDayforOrders(String day1, String day2) throws SQLException {
        ArrayList<OrderView> orderViews = queryDao.searchByDayforOrders(day1,day2);

        ArrayList<OrderViewDto> orderViewDtos = new ArrayList<>();

        for (OrderView orderView : orderViews){
            orderViewDtos.add(new OrderViewDto(
                    orderView.getOrderId(),
                    orderView.getDate(),
                    orderView.getCustId(),
                    orderView.getEmpId(),
                    orderView.getTireId(),
                    orderView.getDescription(),
                    orderView.getPayment_method(),
                    orderView.getQty(),
                    orderView.getTotal_amount()
            ));
        }
        return orderViewDtos;
    }
}
