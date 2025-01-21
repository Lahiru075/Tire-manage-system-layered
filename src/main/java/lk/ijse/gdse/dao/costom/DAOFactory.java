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
        CUSTOMER,USER,SUPPLIER,EMPLOYEE,STOCK,PLACE_ORDER,SUPPLIER_ORDER
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
            default:
                return null;
        }
    }
}
