package lk.ijse.gdse.dao;

import lk.ijse.gdse.entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;
public interface CrudDAO<T extends SuperEntity> extends SuperDAO{
    public List<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T obj) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException; //Delete_By_PrimaryKey_Of_The_Table
    public boolean update(T obj) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException; //Search_By_PrimaryKey_Of_The_Table
    public boolean isExists(String id) throws SQLException, ClassNotFoundException;//Search_By_PrimaryKey_Of_The_Table
}
