package app.model.service;

import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.entity.Pass;

import java.util.List;

public interface PassService {
    public void finishPass(long passId) throws ServiceException;
    public List<Book> getBooksById(long passId) throws ServiceException;
    public void create(Pass pass) throws ServiceException;
    public List<Pass> getAll(String language) throws ServiceException;
    public List<Pass> getAllByUserId(long userID, String language) throws ServiceException;
    public void updatePenalty() throws ServiceException;
    public void addPenalty(int penalty, long passId) throws ServiceException;
}
