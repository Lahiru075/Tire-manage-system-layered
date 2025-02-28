package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.CustomerBo;
import lk.ijse.gdse.dao.costom.CustomerDao;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dto.CustomerDto;
import lk.ijse.gdse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getNextId() throws SQLException {
        String lastId = customerDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("C%03d", newIdIndex);
            return lastId;
        }

        return "C001";
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
//        return customerDao.getAll();
        ArrayList<Customer> customers = customerDao.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers){
            customerDtos.add(new CustomerDto(customer.getCusId(),
                    customer.getCusName(),
                    customer.getEmail(),
                    customer.getContact(),
                    customer.getAddress()
            ));
        }

        return customerDtos;
    }

    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
//        return customerDao.save(customerDto);
        return customerDao.save(new Customer(customerDto.getCusId(),customerDto.getCusName(),customerDto.getEmail(),customerDto.getContact(),customerDto.getAddress()));
    }

    @Override
    public boolean delete(String custId) throws SQLException {
        return customerDao.delete(custId);
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
        return customerDao.update(new Customer(customerDto.getCusId(),customerDto.getCusName(),customerDto.getEmail(),customerDto.getContact(),customerDto.getAddress()));
    }

    @Override
    public boolean checkCustomer(String contact) throws SQLException {
        return customerDao.checkCustomer(contact);
    }

    @Override
    public String getCustomerContactNo() throws SQLException {
        return customerDao.getCustomerContactNo();
    }

    @Override
    public String getCustId(String text) throws SQLException {
        return customerDao.getCustId(text);
    }

    @Override
    public ArrayList<String> getAllCustIds() throws SQLException {
        return customerDao.getAllCustIds();
    }

    @Override
    public CustomerDto getCustomer(String id) throws SQLException {
        Customer customer = customerDao.getCustomer(id);
        CustomerDto customerDto = new CustomerDto(
                customer.getCusId(),
                customer.getCusName(),
                customer.getEmail(),
                customer.getContact(),
                customer.getAddress()
        );
        return customerDto;
    }
}
