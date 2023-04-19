package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.Child_Account;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Child_AccountDAO extends CrudDAO<Child_Account>, SearchByDAO<Child_Account> {

    public Child_Account searchByAccountNumber(String id) throws SQLException, ClassNotFoundException;
    public List<Child_Account> searchByGuardianNic(String nic);
    public List<Child_Account> searchByBranchNo(String branchNo);
    public double getBalanceByChildId(String childID);
//  public boolean updateBalanceByChildAccountNo(String childAccountNo);
    public boolean checkAge(Date dob);
    public boolean addChildDepositBalance(String child_Account_No, double amount) throws SQLException, ClassNotFoundException;
    public boolean addSavingBalance(String accNo, double amount) throws SQLException, ClassNotFoundException;

}
