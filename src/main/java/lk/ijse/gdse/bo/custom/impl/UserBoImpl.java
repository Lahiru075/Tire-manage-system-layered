package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBo;
import lk.ijse.gdse.dao.costom.DAOFactory;
import lk.ijse.gdse.dao.costom.UserDao;
import lk.ijse.gdse.dao.costom.impl.UserDaoImpl;
import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {

    UserDao userDao = (UserDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    public String getNextId() throws SQLException {
        return userDao.getNextId();
    }

    public boolean save(UserDto userDto) throws SQLException {
//        return userDao.save(userDto);
        return userDao.save(new User(userDto.getUsId(), userDto.getRole(), userDto.getPassword(),userDto.getUsername()));
    }

    public ArrayList<UserDto> getAll() throws SQLException {
//        return userDao.getAll();
        ArrayList<User> users = userDao.getAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();

        for (User user : users){
            userDtos.add(new UserDto(user.getUsId(), user.getRole(), user.getPassword(), user.getUsername()));
        }

        return userDtos;
    }

    public boolean update(UserDto userDto) throws SQLException {
        return userDao.update(new User(userDto.getUsId(), userDto.getRole(), userDto.getPassword(),userDto.getUsername()));
    }

    public boolean delete(String userId) throws SQLException {
        return userDao.delete(userId);
    }
}
