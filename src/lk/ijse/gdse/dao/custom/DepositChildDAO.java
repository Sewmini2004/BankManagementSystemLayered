package lk.ijse.gdse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.ChildDeposit;

import java.sql.SQLException;
import java.util.List;

public interface DepositChildDAO extends CrudDAO<ChildDeposit>, SearchByDAO<ChildDeposit> {

 public List<ChildDeposit> searchByChildAccountNb(String childAccNo);
 public  String checkAndGetAccount(String child_Account_No) throws SQLException, ClassNotFoundException;
 public List<ChildDeposit> getDepositByChildAccountNb(String child_Account_No) throws SQLException, ClassNotFoundException;
 public  List<ChildDeposit> getDeposits(String accNo) throws SQLException, ClassNotFoundException;


}
