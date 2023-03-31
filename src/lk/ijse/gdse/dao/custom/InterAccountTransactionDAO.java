package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.InterAccountTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterAccountTransactionDAO extends CrudDAO<InterAccountTransaction>, SearchByDAO<InterAccountTransaction> {
    public ArrayList<InterAccountTransaction> searchBySenderAccount(String savingsAccountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<InterAccountTransaction> searchByReceiverAccount(String savingsAccountNo);


}
