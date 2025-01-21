package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.costom.impl.CustomerDaoImpl;
import lk.ijse.gdse.dao.costom.impl.SupplierDaoImpl;
import lk.ijse.gdse.dao.costom.impl.UserDaoImpl;

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
        CUSTOMER,USER,SUPPLIER
    }

    public SuperDao getDAO(DAOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerDaoImpl();
            case USER:
                return new UserDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            default:
                return null;
        }
    }
}
