package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse.bo.custom.impl.SupplierBoImpl;
import lk.ijse.gdse.bo.custom.impl.UserBoImpl;
import lk.ijse.gdse.dao.costom.impl.SupplierDaoImpl;
import lk.ijse.gdse.dao.costom.impl.UserDaoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }

        return boFactory;
    }

    public enum BOType {
        CUSTOMER,USER,SUPPLIER
    }

    public SuperBo getBO(BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerBoImpl();
            case USER:
                return new UserBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            default:
                return null;
        }
    }
}
