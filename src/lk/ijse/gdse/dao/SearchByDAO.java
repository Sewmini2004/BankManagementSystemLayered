package lk.ijse.gdse.dao;

import java.sql.Date;
import java.util.ArrayList;

public interface SearchByDAO<T> extends SuperDAO{
    public ArrayList<T> searchByDate(Date date);

}