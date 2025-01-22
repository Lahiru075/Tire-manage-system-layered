package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.costom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType{
        CUSTOMER,USER,SUPPLIER,EMPLOYEE,STOCK,PLACE_ORDER,SUPPLIER_ORDER,PAYMENT,DISCOUNT,CONFIRM_ORDER,ORDER,TIRE_ORDER,QUERY
    }

    public SuperDao getDAO(DAOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerDaoImpl();
            case USER:
                return new UserDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case STOCK:
                return new StockDaoImpl();
            case PLACE_ORDER:
                return new PlaceOrderDaoImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case DISCOUNT:
                return new DiscountDaoImpl();
            case CONFIRM_ORDER:
                return new ConformOrderDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case TIRE_ORDER:
                return new TireOrderDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
            default:
                return null;
        }
    }
}
