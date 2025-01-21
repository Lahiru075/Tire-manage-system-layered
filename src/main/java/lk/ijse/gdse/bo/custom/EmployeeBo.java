package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo extends SuperBo{
    ArrayList<String> getAllEmployeesContact() throws SQLException;
    String getEmpId(String value) throws SQLException;
    String getNextId() throws SQLException;
    ArrayList<EmployeeDto> getAll() throws SQLException;
    boolean save(EmployeeDto employeeDto) throws SQLException;
    boolean delete(String empId) throws SQLException;
    boolean update(EmployeeDto employeeDto) throws SQLException;
    ArrayList<String> getAllEmployeesId() throws SQLException;
}
