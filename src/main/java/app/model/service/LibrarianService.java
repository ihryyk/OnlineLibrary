package app.model.service;

import app.model.dao.exeption.ServiceException;
import app.model.entity.User;

import java.util.List;

public interface LibrarianService {
    public void createNew(User librarian) throws ServiceException;
    public void delete(long librarianId) throws ServiceException;
    public List<User> getAll() throws ServiceException;
}
