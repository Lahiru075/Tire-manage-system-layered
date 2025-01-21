package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.CustomerDto;
import lk.ijse.gdse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer> {
    boolean checkCustomer(String contact) throws SQLException;
    String getCustomerContactNo() throws SQLException;
    String getCustId(String text) throws SQLException;
    ArrayList<String> getAllCustIds() throws SQLException;
    Customer getCustomer(String id) throws SQLException;
}
