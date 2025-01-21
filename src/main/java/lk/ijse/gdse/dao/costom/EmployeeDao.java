package lk.ijse.gdse.dao.costom;

import lk.ijse.gdse.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao {
    ArrayList<String> getAllEmployeesContact() throws SQLException;
    String getEmpId(String value) throws SQLException;
    String getNextEmployeeId() throws SQLException;
    ArrayList<EmployeeDto> getAllEmployees() throws SQLException;
    boolean seveEmployee(EmployeeDto employeeDto) throws SQLException;
    boolean deleteEmployee(String empId) throws SQLException;
    boolean updateEmployee(EmployeeDto employeeDto) throws SQLException;
    ArrayList<String> getAllEmployeesId() throws SQLException;
}
