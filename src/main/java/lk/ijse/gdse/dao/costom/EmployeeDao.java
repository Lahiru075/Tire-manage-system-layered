package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dao.CrudDao;
import lk.ijse.gdse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao extends CrudDao<Employee> {
    ArrayList<String> getAllEmployeesContact() throws SQLException;
    String getEmpId(String value) throws SQLException;
    ArrayList<String> getAllEmployeesId() throws SQLException;
}
