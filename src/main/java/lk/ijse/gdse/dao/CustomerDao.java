package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    String getNextCustomerId() throws SQLException;
    ArrayList<CustomerDto> getAllCustomers() throws SQLException;
    boolean saveCustomer(CustomerDto customerDto) throws SQLException;
    boolean deleteCustomer(String custId) throws SQLException;
    boolean updateCustomer(CustomerDto customerDto) throws SQLException;
    boolean checkCustomer(String contact) throws SQLException;
    String getCustomerContactNo() throws SQLException;
    String getCustId(String text) throws SQLException;
    ArrayList<String> getAllCustIds() throws SQLException;
    CustomerDto getCustomer(String id) throws SQLException;
}
