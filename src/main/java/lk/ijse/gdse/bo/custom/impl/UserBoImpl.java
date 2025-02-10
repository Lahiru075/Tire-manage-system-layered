package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBo;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.costom.UserDao;
import lk.ijse.gdse.dto.UserDto;
import lk.ijse.gdse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {

    UserDao userDao = (UserDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    public String getNextId() throws SQLException {
        String lastId = userDao.getNextId();

        if (lastId != null){
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            lastId = String.format("U%03d", newIdIndex);
            System.out.println(lastId);
            return lastId;

        }

        return "U001";
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

    public UserDto checkUser(String username, String password) throws SQLException {
        ArrayList<User> users = userDao.checkUser();

        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return new UserDto(user.getUsId(), user.getRole(), user.getPassword(), user.getUsername());
            }
        }
        return null;

    }
}
