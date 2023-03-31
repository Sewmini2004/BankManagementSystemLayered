package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.Account;

import java.sql.SQLException;

public interface SavingAccountDAO extends CrudDAO<Account>, SearchByDAO<Account> {
public boolean addSavingBalance(String saving_Account_No,double amount) throws SQLException, ClassNotFoundException;
public boolean minusBalanceSavingBalance(String saving_Account_No,double amount) throws SQLException, ClassNotFoundException;
public Account searchByAccountNumber(String id) throws SQLException, ClassNotFoundException;
public Account searchByNic(String nic);
public Account searchByName(String name);
public  double getAccountBalance(String saving_account_no) throws SQLException, ClassNotFoundException;

}