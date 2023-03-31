package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.InterAccountTransactionDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.InterAccountTransaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InterAccountTransactionDAOImple implements InterAccountTransactionDAO {

    @Override
    public ArrayList<InterAccountTransaction> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("select * from inter_account_transaction");
        ArrayList<InterAccountTransaction>  interAccountTransactions=new ArrayList<>();
        while (resultSet.next()){
            interAccountTransactions.add(new InterAccountTransaction(
                            resultSet.getString(1),
                            resultSet.getDouble(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return interAccountTransactions;
    }

    @Override
    public boolean add(InterAccountTransaction interAccountTransaction) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "INSERT INTO inter_account_transaction  VALUES(?,?,?,?,?,?)",
                interAccountTransaction.getTransaction_Id(),
                interAccountTransaction.getAmount(),
                interAccountTransaction.getDate(),
                interAccountTransaction.getTime(),
                interAccountTransaction.getAccount_01(),
                interAccountTransaction.getAccount_02()
        );


    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(InterAccountTransaction obj) {
        return false;
    }

    @Override
    public InterAccountTransaction search(String transaction_Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM SELECT  * FROM inter_account_transaction WHERE transaction_Id=?", transaction_Id);
   if(resultSet.next()){
       return new InterAccountTransaction(
               resultSet.getString(1),
               resultSet.getDouble(2),
               resultSet.getString(3),
               resultSet.getString(4),
               resultSet.getString(5),
               resultSet.getString(6)
       );
   }
   return null;
    }

    @Override
    public boolean isExists(String transaction_Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT  * FROM inter_account_transaction WHERE transaction_Id=?", transaction_Id);
      return  resultSet.next();
    }

    @Override
    public ArrayList<InterAccountTransaction> searchByDate(Date date) {
        return null;
    }

    @Override
    public ArrayList<InterAccountTransaction> searchBySenderAccount(String savingsAccountNo) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("select * from inter_account_transaction",savingsAccountNo);
        ArrayList<InterAccountTransaction>  interAccountTransactions=new ArrayList<>();
        while (resultSet.next()){
            interAccountTransactions.add(new InterAccountTransaction(
                            resultSet.getString(1),
                            resultSet.getDouble(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return interAccountTransactions;
    }

    @Override
    public ArrayList<InterAccountTransaction> searchByReceiverAccount(String savingsAccountNo) {
        return null;
    }
}
