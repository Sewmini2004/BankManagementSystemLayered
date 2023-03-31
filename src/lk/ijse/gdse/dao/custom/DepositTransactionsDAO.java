package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.Deposit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepositTransactionsDAO extends CrudDAO<Deposit>, SearchByDAO<Deposit> {
    public ArrayList<Deposit> getSearchBySavingsAccountNb(String savingsAccountNo) throws SQLException, ClassNotFoundException;
    public List<Deposit> getAllDepositsTM(String accNo) throws SQLException, ClassNotFoundException;
    public String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException;


}
