package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.CustomerDao;
import lk.ijse.gdse.dto.CustomerDto;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select custId from customer order by custId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            customers.add(customer);
        }
        return customers;
    }

    public boolean save(Customer customer) throws SQLException {

        return CrudUtil.execute("insert into customer values (?,?,?,?,?)",
                customer.getCusId(),
                customer.getCusName(),
                customer.getEmail(),
                customer.getContact(),
                customer.getAddress()
        );
    }

    public boolean delete(String custId) throws SQLException {
        return CrudUtil.execute("delete from customer where custId=?", custId);
    }

    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute("update customer set name=?, email=?, coNo=?, address=? where custId=?",
                customer.getCusName(),
                customer.getEmail(),
                customer.getContact(),
                customer.getAddress(),
                customer.getCusId()
        );
    }

    public boolean checkCustomer(String contact) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where coNo=?", contact);

        if (rst.next()) {
            return true;
        }

        return false;
    }

    public String getCustomerContactNo() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer order by custId desc limit 1");

        if (rst.next()) {
            return rst.getString(4);
        }
        return null;
    }

    public String getCustId(String text) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where coNo=?", text);

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public ArrayList<String> getAllCustIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }
        return customerIds;
    }

    public Customer getCustomer(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where custId = ?",id);

        if (rst.next()){
            Customer customer = new Customer();
            customer.setCusId(rst.getString(1));
            customer.setCusName(rst.getString(2));
            customer.setEmail(rst.getString(3));
            customer.setContact(rst.getString(4));
            customer.setAddress(rst.getString(5));

            return customer;

        }
        return null;
    }
}
