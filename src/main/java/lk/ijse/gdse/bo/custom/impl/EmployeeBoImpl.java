package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.EmployeeBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.EmployeeDao;
import lk.ijse.gdse.dto.EmployeeDto;
import lk.ijse.gdse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao employeeDao = (EmployeeDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    public ArrayList<String> getAllEmployeesContact() throws SQLException {
        return employeeDao.getAllEmployeesContact();
    }

    public String getEmpId(String value) throws SQLException {
        return employeeDao.getEmpId(value);
    }

    public String getNextId() throws SQLException {
        String lastId = employeeDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("E%03d", newIdIndex);
            return lastId;
        }

        return "E001";
    }

    public ArrayList<EmployeeDto> getAll() throws SQLException {
//        return employeeDao.getAll();
        ArrayList<Employee> employees = employeeDao.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees){
            employeeDtos.add(new EmployeeDto(employee.getEmpId(), employee.getName(), employee.getRole(), employee.getEmail(), employee.getAddress(), employee.getContact(), employee.getSalary()));
        }

        return employeeDtos;
    }

    public boolean save(EmployeeDto employeeDto) throws SQLException {
//        return employeeDao.save(employeeDto);
        return employeeDao.save(new Employee(employeeDto.getEmpId(), employeeDto.getName(), employeeDto.getRole(), employeeDto.getEmail(), employeeDto.getAddress(), employeeDto.getContact(), employeeDto.getSalary()));
    }

    public boolean delete(String empId) throws SQLException {
        return employeeDao.delete(empId);
    }

    public boolean update(EmployeeDto employeeDto) throws SQLException {
        return employeeDao.update(new Employee(employeeDto.getEmpId(), employeeDto.getName(), employeeDto.getRole(), employeeDto.getEmail(), employeeDto.getAddress(), employeeDto.getContact(), employeeDto.getSalary()));
    }

    public ArrayList<String> getAllEmployeesId() throws SQLException {
        return employeeDao.getAllEmployeesId();
    }
}
