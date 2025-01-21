package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo{
    String getNextId() throws SQLException;
    ArrayList<CustomerDto> getAll() throws SQLException;
    boolean save(CustomerDto customerDto) throws SQLException;
    boolean delete(String custId) throws SQLException;
    boolean update(CustomerDto customerDto) throws SQLException;
    boolean checkCustomer(String contact) throws SQLException;
    String getCustomerContactNo() throws SQLException;
    String getCustId(String text) throws SQLException;
    ArrayList<String> getAllCustIds() throws SQLException;
    CustomerDto getCustomer(String id) throws SQLException;
}
