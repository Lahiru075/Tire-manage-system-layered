package lk.ijse.gdse.bo;

import lk.ijse.gdse.bo.custom.impl.*;

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
        CUSTOMER,USER,SUPPLIER,EMPLOYEE,STOCK,PLACE_ORDER,SUPPLIER_ORDER,PAYMENT,DISCOUNT,CONFIRM_ORDER,ORDER,REPORT,VIEW_ORDER,TIRE_ORDER
    }

    public SuperBo getBO(BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerBoImpl();
            case USER:
                return new UserBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case STOCK:
                return new StockBoImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case DISCOUNT:
                return new DiscountBoImpl();
            case CONFIRM_ORDER:
                return new ConformOrderBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case REPORT:
                return new ReportBoImpl();
            case VIEW_ORDER:
                return new ViewOrderBoImpl();
            case TIRE_ORDER:
                return new TireOrderBoImpl();
            default:
                return null;
        }
    }
}
