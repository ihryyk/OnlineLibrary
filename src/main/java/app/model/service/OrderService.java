package app.model.service;

import app.model.dao.exeption.ServiceException;
import app.model.entity.Order;

import java.util.List;

public interface OrderService {
    public void cancel(int orderId) throws ServiceException;
    public List<Order> getAllByUserId(long userID, String language) throws ServiceException;
    public Order getById(String language, long orderId) throws ServiceException;
    public List<Order> getAll(String language) throws ServiceException;
    public void create(Order order) throws ServiceException;
    public void  submit(long orderId) throws ServiceException;
}
