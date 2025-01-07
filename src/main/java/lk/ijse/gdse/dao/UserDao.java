package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    String getNextId() throws SQLException;
    boolean seveUser(UserDto userDto) throws SQLException;
    ArrayList<UserDto> getAllUsers() throws SQLException;
    boolean updateUser(UserDto userDto) throws SQLException;
    boolean deleteUser(String userId) throws SQLException;
}
