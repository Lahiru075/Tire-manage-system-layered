package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBo extends SuperBo{
    String getNextId() throws SQLException;
    boolean save(UserDto userDto) throws SQLException;
    ArrayList<UserDto> getAll() throws SQLException;
    boolean update(UserDto userDto) throws SQLException;
    boolean delete(String userId) throws SQLException;
    UserDto checkUser(String username, String password) throws SQLException;
}
