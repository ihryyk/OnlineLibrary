package app.model.service;

import app.model.dao.exeption.ServiceException;
import app.model.entity.User;

import java.util.List;

public interface UserService {
    public void add(User user) throws ServiceException;
    public void update(User user) throws ServiceException;
    public User getByEmailAndPassword(String email, String password) throws ServiceException;
    public User getByEmail(String email) throws ServiceException;
    public List<User> getAll() throws ServiceException;
    public void block(long userId) throws ServiceException;
    public void unlock(long userId) throws ServiceException;
    public void delete(User user) throws ServiceException;
}
