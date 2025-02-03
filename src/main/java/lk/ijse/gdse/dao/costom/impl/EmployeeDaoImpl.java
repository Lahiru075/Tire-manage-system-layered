package lk.ijse.gdse.dao.costom.impl;

import lk.ijse.gdse.dao.costom.EmployeeDao;
import lk.ijse.gdse.entity.Employee;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {

    public ArrayList<String> getAllEmployeesContact() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<String> employees = new ArrayList<>();

        while (rst.next()) {
            employees.add(rst.getString(6));
        }
        return employees;
    }


    public String getEmpId(String value) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT empId FROM employee WHERE coNo=?", value);

        if (resultSet != null && resultSet.next()) {
            return resultSet.getString("empId");
        }
        return null;
    }

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select empId from employee order by empId desc limit 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<Employee> employees = new ArrayList<>();

        while (rst.next()) {
            Employee employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)
            );
            employees.add(employee);
        }
        return employees;
    }

    public boolean save(Employee employee) throws SQLException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?,?,?)",
                employee.getEmpId(),
                employee.getName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContact(),
                employee.getSalary()
        );
    }

    public boolean delete(String empId) throws SQLException {
        return CrudUtil.execute("delete from employee where empId=?", empId);
    }

    public boolean update(Employee employee) throws SQLException {
        return CrudUtil.execute("update employee set name=?, role=?, email=?, address=?, coNo=?, salary=? where empId=?",
                employee.getName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContact(),
                employee.getSalary(),
                employee.getEmpId()
        );
    }

    public ArrayList<String> getAllEmployeesId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from employee");
        ArrayList<String> employees = new ArrayList<>();

        while (rst.next()) {
            employees.add(rst.getString(1));
        }
        return employees;
    }
}
